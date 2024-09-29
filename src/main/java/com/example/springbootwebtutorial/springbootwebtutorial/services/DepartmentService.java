package com.example.springbootwebtutorial.springbootwebtutorial.services;

import com.example.springbootwebtutorial.springbootwebtutorial.advices.ResourceNotFoundException;
import com.example.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDto;
import com.example.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDto;
import com.example.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import com.example.springbootwebtutorial.springbootwebtutorial.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public boolean existsById(Long id) {
        boolean exists = departmentRepository.existsById(id);
        if(!exists)
            throw new ResourceNotFoundException("Department not found by id: " + id);

        return true;
    }

    public List<DepartmentDto> getAllDepartments() {

        List<DepartmentEntity> allDepartments = departmentRepository.findAll();
        return allDepartments.stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    public DepartmentDto createNewDepartment(DepartmentDto departmentDto) {

        if(departmentRepository.existsByTitle(departmentDto.getTitle())) {
            throw new IllegalArgumentException("A department with the same title already exists.");
        } else {
            DepartmentEntity departmentEntityToBeSaved = modelMapper.map(departmentDto, DepartmentEntity.class);
            DepartmentEntity savedEntity = departmentRepository.save(departmentEntityToBeSaved);
            return modelMapper.map(savedEntity, DepartmentDto.class);
        }
    }

    public Optional<DepartmentDto> getDepartmentById(Long id) {
        existsById(id);
         return departmentRepository.findById(id)
                    .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDto.class));
    }

    public DepartmentDto upateDepartmentById(DepartmentDto departmentDto, Long id) {

        // if record exists, we update the info with new request body or else we create a new record
        existsById(id);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDto, DepartmentEntity.class);
        departmentEntity.setId(id);
        DepartmentEntity savedEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedEntity, DepartmentDto.class);
    }

    public boolean deleteDepartmentById(Long id) {
        existsById(id);
        departmentRepository.deleteById(id);
        return true;
    }
}

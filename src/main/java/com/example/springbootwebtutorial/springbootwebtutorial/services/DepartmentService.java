package com.example.springbootwebtutorial.springbootwebtutorial.services;

import com.example.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDto;
import com.example.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import com.example.springbootwebtutorial.springbootwebtutorial.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDto> getAllDepartments() {

        List<DepartmentEntity> allDepartments = departmentRepository.findAll();
        return allDepartments.stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    public DepartmentDto createNewDepartment(DepartmentDto departmentDto) {
        DepartmentEntity departmentEntityToBeSaved = modelMapper.map(departmentDto, DepartmentEntity.class);
        DepartmentEntity savedEntity = departmentRepository.save(departmentEntityToBeSaved);
        return modelMapper.map(savedEntity, DepartmentDto.class);
    }
}

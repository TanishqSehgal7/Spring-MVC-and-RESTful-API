package com.example.springbootwebtutorial.springbootwebtutorial.services;

import com.example.springbootwebtutorial.springbootwebtutorial.advices.ResourceNotFoundException;
import com.example.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDto;
import com.example.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.example.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    public boolean isExistsByEmployeeId(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists)
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        return true;
    }

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDto> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class));
    }

    public List<EmployeeDto> getAllEmployyes() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto createNewEmployee(EmployeeDto inputEmployee) {

        EmployeeEntity entityToBeSaved = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(entityToBeSaved);
        return modelMapper.map(savedEmployeeEntity,EmployeeDto.class);
    }

    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long employeeId) {
        // check if the employeeId is present and update in that case only
        // or else create a new employee with that employee id

        isExistsByEmployeeId(employeeId);

        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDto.class);
    }

    public Boolean deleteEmployeeById(Long employeeId) throws Exception {

        isExistsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }


    public EmployeeDto updatePartialEmployeeById(Map<String, Object> updates, Long employeeId) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        updates.forEach((field, value) -> {
            Field fieldToUpdate = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, employeeEntity, value);
        });
        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity,EmployeeDto.class);
    }
}

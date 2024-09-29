package com.example.springbootwebtutorial.springbootwebtutorial.controllers;

import com.example.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDto;
import com.example.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDto;
import com.example.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import com.example.springbootwebtutorial.springbootwebtutorial.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {


    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    } // dependency injected

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping()
    public ResponseEntity<DepartmentDto> createNewDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.createNewDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

}

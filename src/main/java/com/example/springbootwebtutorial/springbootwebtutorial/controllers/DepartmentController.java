package com.example.springbootwebtutorial.springbootwebtutorial.controllers;

import com.example.springbootwebtutorial.springbootwebtutorial.advices.ResourceNotFoundException;
import com.example.springbootwebtutorial.springbootwebtutorial.dto.DepartmentDto;
import com.example.springbootwebtutorial.springbootwebtutorial.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(name = "id") Long id) {

        Optional<DepartmentDto> departmentDto = departmentService.getDepartmentById(id);

        return departmentDto.map(departmentDto1 -> ResponseEntity.ok(departmentDto1))
                .orElseThrow(() -> new ResourceNotFoundException("Department not founnd with id: " + id));
    }

    @PostMapping()
    public ResponseEntity<DepartmentDto> createNewDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.createNewDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@RequestBody @Valid DepartmentDto departmentDto, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(departmentService.upateDepartmentById(departmentDto,id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable(name = "id") Long id) {
        boolean isDeleted = departmentService.deleteDepartmentById(id);
        if(isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

package com.example.springbootwebtutorial.springbootwebtutorial.controllers;

import com.example.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDto;
import com.example.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*
         @RestController ensures that the controller is REST in nature
         it also extends @Controller and @ResponseBody annotation
         which converts XML/JSON data to java object

         @ComponentScan in @SpringBootApplication handles all the mappings defined inside
         controllers

         Java object is automatically coverted to JSON object when url is opened on localhost
    */

    @GetMapping(path = "/getSecretMessage")
    public String getMySecretMessage() {
        return "Secret Message: afgsrgw@42e3qe";
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        Optional<EmployeeDto> employeeDto = employeeService.getEmployeeById(id);
        if (employeeDto == null) return ResponseEntity.notFound().build();
        return employeeDto.map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                      @RequestParam(required = false) String sortBy) {
                                    // optional parameter using required=false
        return ResponseEntity.ok(employeeService.getAllEmployyes());
    }

    @PostMapping()
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody @Valid EmployeeDto inputEmployee) {
        EmployeeDto savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED); // 201 status code
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@RequestBody EmployeeDto employeeDto, @PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDto, employeeId));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId) throws Exception {
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if (gotDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployeeById(@RequestBody Map<String,Object> updates,
                                                 @PathVariable Long employeeId) {

        EmployeeDto employeeDto = employeeService.updatePartialEmployeeById(updates, employeeId);
        if (employeeDto == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(employeeDto);
    }

}

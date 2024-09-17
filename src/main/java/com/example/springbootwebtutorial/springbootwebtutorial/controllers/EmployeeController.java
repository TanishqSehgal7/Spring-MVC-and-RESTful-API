package com.example.springbootwebtutorial.springbootwebtutorial.controllers;

import com.example.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDto;
import com.example.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.example.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.example.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping()
    public List<EmployeeDto> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy) {
                                    // optional parameter using required=false
        return employeeService.getAllEmployyes();
    }

    @PostMapping()
    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto inputEmployee) {
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeById() {
        return "Hello from PUT";
    }

}

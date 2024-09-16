package com.example.springbootwebtutorial.springbootwebtutorial.controllers;

import com.example.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

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
        return new EmployeeDto(id,"Tanishq","tanishqsehgal.ts@gmail.com",
                24, LocalDate.of(2024,1,1),true);
    }

    @GetMapping()
    public String getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                  @RequestParam(required = false) String sortBy) {
                                    // optional parameter using required=false
        return "Employee age is: " + age + ", Sorted By " + sortBy;
    }

    @PostMapping()
    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto inputEmployee) {
        inputEmployee.setId(100L);
        return inputEmployee;
    }

    @PutMapping
    public String updateEmployeeById() {
        return "Hello from PUT";
    }

}

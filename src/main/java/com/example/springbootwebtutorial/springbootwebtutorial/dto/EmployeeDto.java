package com.example.springbootwebtutorial.springbootwebtutorial.dto;

import com.example.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeRoleValidationAnnotation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class EmployeeDto {

    // Data Transfer Object

    private Long id;
    @NotBlank(message = "Name of the employee cannot be blank!")
    @Size(min = 3, max = 30, message = "Number of characters in the field {name} should be between 3 and 20")
    private String name;

    @Email(message = "Email should be a in a valid format.")
    @NotBlank(message = "The email of the employee cannot be blank.")
    private String email;

    @Min(value = 10, message = "Age cannot be less than 10")
    @Max(value = 70, message = "Age cannot be greater than 70")
    private Integer age;

    @PastOrPresent(message = "dateOfJoining field cannot be in the future")
    private LocalDate dateOfJoining;

    @JsonProperty("isActive") // fix for the null value issue of isActive in response
    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;

//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of Employee can either be USER or ADMIN")
    @NotBlank(message = "The role of the employee cannot be blank.")
    @EmployeeRoleValidationAnnotation
    private String role; // ADMIN or USER

    @Positive(message = "The field salary must contain a positive value.")
    @NotNull(message = "Salary of an employee cannot be null")
    @Digits(integer = 6, fraction = 2, message = "The salary can be in the form xxxxxx.xx")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "10000.50")
    private Double salary;

    public EmployeeDto() { // default constructor
    }

    public EmployeeDto(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dateOfJoining = dateOfJoining;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

   public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
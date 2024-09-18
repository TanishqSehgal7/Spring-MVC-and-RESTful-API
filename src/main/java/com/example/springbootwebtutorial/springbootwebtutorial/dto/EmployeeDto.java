package com.example.springbootwebtutorial.springbootwebtutorial.dto;

import com.example.springbootwebtutorial.springbootwebtutorial.controllers.EmployeeController;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class EmployeeDto {

    // Data Transfer Object

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;

    @JsonProperty("isActive") // fix for the null value issue of isActive in response
    private Boolean isActive;

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
}

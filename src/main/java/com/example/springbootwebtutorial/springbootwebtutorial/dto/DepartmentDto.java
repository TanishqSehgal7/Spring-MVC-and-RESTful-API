package com.example.springbootwebtutorial.springbootwebtutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long id;

    @NotBlank(message = "Department name cannot be blank.")
    private String title;

    @AssertTrue(message = "Department should be active.")
    @NotNull(message = "isActive for department cannot be blank")
    @JsonProperty("isActive")
    private boolean isActive;

    @PastOrPresent(message = "created date for department should be either a past date or the current date")
    @NotNull(message = "created date cannot be blank")
    private LocalDate createdAt;
}
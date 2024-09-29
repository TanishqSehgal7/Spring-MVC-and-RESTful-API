package com.example.springbootwebtutorial.springbootwebtutorial.dto;

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

//    @NotNull(message = "Department id cannot be null")
    private Long id;

//    @NotBlank(message = "Department name cannot be blank.")
    private String title;

//    @AssertTrue(message = "Department should be active.")
//    @NotBlank(message = "isActive for department cannot be blank")
    private boolean isActive;

//    @PastOrPresent(message = "created date for department should be either a past date or the current date")
//    @NotBlank(message = "created date cannot be blank")
//    @Pattern(regexp = "yyyy-MM-dd")
    private LocalDate createdAt;
}
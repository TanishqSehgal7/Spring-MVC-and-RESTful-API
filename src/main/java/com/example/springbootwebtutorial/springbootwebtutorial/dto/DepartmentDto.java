package com.example.springbootwebtutorial.springbootwebtutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

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

    @Range(min = 5, max = 25, message = "Team Size should be between 5 to 25")
    private Integer teamSize;

    @NotBlank(message = "Manager Name cannot be blank or null.")
    private String deptManagerName;

    @NotNull(message = "Department Code cannot be blank.")
    @PositiveOrZero(message = "Department Code cannot be a negative value")
    private Integer departmentCode;

    @Min(value = 100000, message = "Budget must be atleast Rs. 100000")
    @Max(value = 500000, message = "Budget cannot be more than Rs. 500000")
    private Integer budget;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Department Email cannot be blank.")
    private String departmentEmail;

}
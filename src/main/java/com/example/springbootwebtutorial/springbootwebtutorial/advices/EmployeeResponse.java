package com.example.springbootwebtutorial.springbootwebtutorial.advices;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeResponse<T> {

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timeStamp;
    private T employees;
    private ApiError error;

    public EmployeeResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public EmployeeResponse(T employees) {
        this();
        this.employees = employees;
    }

    public EmployeeResponse(ApiError error) {
        this();
        this.error = error;
    }
}

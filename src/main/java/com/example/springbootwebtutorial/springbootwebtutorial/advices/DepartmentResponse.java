package com.example.springbootwebtutorial.springbootwebtutorial.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentResponse<T> {
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timeStamp;
    private T departments;
    private ApiError error;

    public DepartmentResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public DepartmentResponse(T departments) {
        this();
        this.departments = departments;
    }

    public DepartmentResponse(ApiError error) {
        this();
        this.error = error;
    }
}

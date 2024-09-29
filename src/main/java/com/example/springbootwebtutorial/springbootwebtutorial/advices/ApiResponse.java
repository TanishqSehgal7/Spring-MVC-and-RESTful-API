package com.example.springbootwebtutorial.springbootwebtutorial.advices;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timeStamp;
    private T objects;
    private ApiError error;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T objects) {
        this();
        this.objects = objects;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }
}

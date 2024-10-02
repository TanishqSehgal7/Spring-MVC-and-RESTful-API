package com.example.springbootwebtutorial.springbootwebtutorial.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class DepartmentExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DepartmentResponse<?>> handleRsourceNotFoundExceptionForDepartments(ResourceNotFoundException exception) {

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();

        return buildDepartmentResponseEntityWithApiResponse(apiError);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DepartmentResponse<?>> handleAnyOtherExceptionForDepartments(Exception exception){

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();

        return buildDepartmentResponseEntityWithApiResponse(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DepartmentResponse<?>> handleInputValidationErrorsForDepartments(MethodArgumentNotValidException exception) {

        List<String> errors = exception.getBindingResult()
                .getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .subErrors(errors)
                .build();

        return buildDepartmentResponseEntityWithApiResponse(apiError);
    }


    public ResponseEntity<DepartmentResponse<?>> buildDepartmentResponseEntityWithApiResponse(ApiError apiError) {

        return new ResponseEntity<>(new DepartmentResponse<>(apiError), apiError.getStatus());
    }

}

package com.example.springbootwebtutorial.springbootwebtutorial.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<EmployeeResponse<?>> handleResourceNotFoundForEmployees(ResourceNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();

        return buildEmployeeResponseEntityForEmployees(apiError);
    }

    // Handling more generalised exceptions

    @ExceptionHandler(Exception.class)
    public ResponseEntity<EmployeeResponse<?>> handleAnyExceptionForEmployees(Exception exception) {
        ApiError apiError =  ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();

        return buildEmployeeResponseEntityForEmployees(apiError);
    }


    // this exception handler is for giving a more readable/understandable error message to the end user
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EmployeeResponse<?>> handleInputValidationErrorsForEmployees(MethodArgumentNotValidException exception) {
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input Validation Failed!")
                .subErrors(errors)
                .build();

        return buildEmployeeResponseEntityForEmployees(apiError);
    }

    private ResponseEntity<EmployeeResponse<?>> buildEmployeeResponseEntityForEmployees(ApiError apiError) {
        return new ResponseEntity<>(new EmployeeResponse<>(apiError), apiError.getStatus());
    }

}

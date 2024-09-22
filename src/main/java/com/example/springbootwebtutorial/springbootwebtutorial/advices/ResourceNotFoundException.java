package com.example.springbootwebtutorial.springbootwebtutorial.advices;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }


}

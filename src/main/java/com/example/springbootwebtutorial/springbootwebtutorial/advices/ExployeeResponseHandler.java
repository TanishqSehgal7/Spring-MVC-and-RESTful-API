package com.example.springbootwebtutorial.springbootwebtutorial.advices;

import com.example.springbootwebtutorial.springbootwebtutorial.controllers.EmployeeController;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

@RestControllerAdvice
public class ExployeeResponseHandler implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

//        if (body instanceof EmployeeResponse<?> || body instanceof DepartmentResponse<?>) {
//            System.out.println("Sent Without Wrapping");
//            return body;
//        }

        if (returnType.getContainingClass().getSimpleName().contains("Employee")) {
            System.out.println("Wrapping as per Employee");
            return new EmployeeResponse<>(body);  // Wrap for employee-related responses
        } else if (returnType.getContainingClass().getSimpleName().contains("Department")) {
            System.out.println("Wrapping as per Department");
            return new DepartmentResponse<>(body);  // Wrap for department-related responses
        }
        return body;
    }
}

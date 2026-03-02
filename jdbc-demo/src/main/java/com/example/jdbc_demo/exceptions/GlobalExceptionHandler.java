package com.example.jdbc_demo.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomMessageException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustomMessageException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("Status", ex.getHttpStatus());
        response.put("Message", ex.getMessage());

        return new ResponseEntity<>(response,ex.getHttpStatus());

    }
}

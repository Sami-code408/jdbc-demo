package com.example.jdbc_demo.exceptions;
import org.springframework.http.HttpStatus;

public class CustomMessageException extends RuntimeException{
    private final HttpStatus status;


    public CustomMessageException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
    public HttpStatus getHttpStatus(){
        return status;

    }

}


package com.realProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

@ControllerAdvice
public class ExceptionHandeling {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> Exception(
            Exception e
    ){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

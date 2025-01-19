package com.example.demo.handler;


import com.example.demo.exception.ParameterNotFoundException;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler({ParameterNotFoundException.class})
    public ResponseEntity<String> handleParameterNotFoundException(ParameterNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


}

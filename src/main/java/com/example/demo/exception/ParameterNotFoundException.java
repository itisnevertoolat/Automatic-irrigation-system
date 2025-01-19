package com.example.demo.exception;

public class ParameterNotFoundException extends Exception{

    public ParameterNotFoundException(String message){
        super(String.format("Required parameter %s missing", message));
    }
}

package com.example.demo.exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String message){
        super(String.format("We can't find %s with the provide id", message));
    }
}

package com.app.devblogservice.exception;

public class InvalidRequestException extends Exception{
    public InvalidRequestException(String message){
        super(message);
    }
}

package com.silver.crudapp.exception;


public class NoSuchUserExistsException extends RuntimeException{
    public NoSuchUserExistsException(String message){
        super(message);
    }
}

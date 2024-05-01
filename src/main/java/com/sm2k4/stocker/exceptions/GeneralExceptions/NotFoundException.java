package com.sm2k4.stocker.exceptions.GeneralExceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}

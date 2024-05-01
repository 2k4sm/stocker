package com.sm2k4.stocker.exceptions.GeneralExceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}

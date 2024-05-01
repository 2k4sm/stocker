package com.sm2k4.stocker.exceptions.GeneralExceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}

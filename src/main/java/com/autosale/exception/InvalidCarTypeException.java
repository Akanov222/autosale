package com.autosale.exception;

public class InvalidCarTypeException extends RuntimeException {
    public InvalidCarTypeException(String message) {
        super(message);
    }
}

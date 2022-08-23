package com.victor.noloosecoins.exceptions;

public class AlreadyRegisteredDescription extends RuntimeException{
    public AlreadyRegisteredDescription(String message) {
        super(message);
    }

    public AlreadyRegisteredDescription(String message, Throwable cause) {
        super(message, cause);
    }
}

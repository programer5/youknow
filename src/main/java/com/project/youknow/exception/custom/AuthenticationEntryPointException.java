package com.project.youknow.exception.custom;

public class AuthenticationEntryPointException extends RuntimeException {

    public AuthenticationEntryPointException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationEntryPointException(String message) {
        super(message);
    }

    public AuthenticationEntryPointException() {
        super();
    }
}

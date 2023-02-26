package com.project.youknow.exception.custom;

public class ApiOtherException extends RuntimeException {

    public ApiOtherException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiOtherException(String message) {
        super(message);
    }

    public ApiOtherException() {
        super();
    }
}

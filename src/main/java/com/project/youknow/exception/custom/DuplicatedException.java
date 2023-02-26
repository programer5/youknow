package com.project.youknow.exception.custom;

public class DuplicatedException extends RuntimeException {

    public DuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedException(String message) {
        super(message);
    }

    public DuplicatedException() {
        super();
    }
}

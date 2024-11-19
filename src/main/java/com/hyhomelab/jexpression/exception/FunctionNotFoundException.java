package com.hyhomelab.jexpression.exception;

public class FunctionNotFoundException extends RuntimeException{
    public FunctionNotFoundException() {
    }

    public FunctionNotFoundException(String message) {
        super(message);
    }

    public FunctionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FunctionNotFoundException(Throwable cause) {
        super(cause);
    }

    public FunctionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

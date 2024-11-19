package com.hyhomelab.jexpression.exception;

public class VarNotFoundException  extends RuntimeException{
    public VarNotFoundException() {
    }

    public VarNotFoundException(String message) {
        super(message);
    }

    public VarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VarNotFoundException(Throwable cause) {
        super(cause);
    }

    public VarNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

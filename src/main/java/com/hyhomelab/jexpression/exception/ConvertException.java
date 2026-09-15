package com.hyhomelab.jexpression.exception;

public class ConvertException extends RuntimeException{

    private static String formatMsg(String message){
        return String.format("[Convert] %s", message);
    }

    public ConvertException() {
        super(formatMsg("error"));
    }

    public ConvertException(String message) {
        super(formatMsg(message));
    }

    public ConvertException(String message, Throwable cause) {
        super(formatMsg(message), cause);
    }

    public ConvertException(Throwable cause) {
        super(cause);
    }

    public ConvertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(formatMsg(message), cause, enableSuppression, writableStackTrace);
    }
}

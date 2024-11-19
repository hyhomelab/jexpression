package com.hyhomelab.jexpression.exception;

public class ScannerException extends RuntimeException{

    private static String formatMsg(String message){
        return String.format("[Scanner] %s", message);
    }

    public ScannerException() {
        super(formatMsg("error"));
    }

    public ScannerException(String message) {
        super(formatMsg(message));
    }

    public ScannerException(String message, Throwable cause) {
        super(formatMsg(message), cause);
    }

    public ScannerException(Throwable cause) {
        super(cause);
    }

    public ScannerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(formatMsg(message), cause, enableSuppression, writableStackTrace);
    }
}

package com.twinkles.talkamout.exceptions;

public class InvalidLicenceNumberException extends RuntimeException {
    private final int statusCode;
    public InvalidLicenceNumberException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}

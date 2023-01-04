package com.twinkles.talkamout.exceptions;

public class UserAlreadyExistException extends RuntimeException{
    private final int statusCode;
    public UserAlreadyExistException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}

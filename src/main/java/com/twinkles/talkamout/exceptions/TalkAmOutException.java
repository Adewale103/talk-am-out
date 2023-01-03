package com.twinkles.talkamout.exceptions;

import lombok.Getter;

@Getter
public class TalkAmOutException extends RuntimeException{
    private final int statusCode;
    public TalkAmOutException(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

}

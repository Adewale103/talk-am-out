package com.twinkles.talkamout.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TalkAmOutException.class)
    public ResponseEntity<?> handleTalkAmOutException(TalkAmOutException talkAmOutException){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(talkAmOutException.getMessage())
                .isSuccessful(false)
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.valueOf(talkAmOutException.getStatusCode()));
    }
}

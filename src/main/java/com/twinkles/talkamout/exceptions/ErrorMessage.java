package com.twinkles.talkamout.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class ErrorMessage {
    private LocalDateTime timeStamp;
    private String message;
    private boolean isSuccessful;
}

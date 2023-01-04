package com.twinkles.talkamout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDto {
    private String response;
    private int questionNumber;
    private Long questionId;
}

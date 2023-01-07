package com.twinkles.talkamout.controllers;

import com.twinkles.talkamout.dto.QuestionDto;
import com.twinkles.talkamout.services.question.QuestionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestParam @NonNull String question){
        QuestionDto questionDto = questionService.addQuestion(question);
        return new ResponseEntity<>(questionDto, HttpStatus.CREATED);
    }
}

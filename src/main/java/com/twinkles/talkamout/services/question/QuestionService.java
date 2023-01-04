package com.twinkles.talkamout.services.question;

import com.twinkles.talkamout.dto.QuestionDto;
import com.twinkles.talkamout.model.Question;

import java.util.List;

public interface QuestionService {
    QuestionDto addQuestion(String question);
    List<Question> loadAllQuestions();
}

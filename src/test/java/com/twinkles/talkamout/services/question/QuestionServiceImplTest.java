package com.twinkles.talkamout.services.question;

import com.twinkles.talkamout.dto.QuestionDto;
import com.twinkles.talkamout.model.Question;
import com.twinkles.talkamout.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;
    private QuestionService questionService;
    private  Question questionObject;
    private  String question;

    @BeforeEach
    void setUp() {
        question = "Have you had suicidal thoughts lately?";
        questionService = new QuestionServiceImpl(questionRepository);
        questionObject = new Question();
        questionObject.setQuestion(question);
        questionObject.setQuestionNumber(1);
    }

    @Test
    public void addQuestionTest(){
        when(questionRepository.existsByQuestion(question)).thenReturn(false);
        when(questionRepository.save(any(Question.class))).thenReturn(questionObject);
        QuestionDto questionDto = questionService.addQuestion(question);
        assertThat(questionDto.getQuestion()).isEqualTo("Have you had suicidal thoughts lately?");
    }

}
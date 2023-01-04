package com.twinkles.talkamout.services.question;

import com.twinkles.talkamout.dto.QuestionDto;
import com.twinkles.talkamout.exceptions.TalkAmOutException;
import com.twinkles.talkamout.model.Question;
import com.twinkles.talkamout.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;
    private static int questionNumberCounter;

    @Override
    public QuestionDto addQuestion(String question) {
        if(questionRepository.existsByQuestion(question)){
            throw new TalkAmOutException("Question already exist in the database ",400);
        }
        Question questionObject = new Question();
        questionObject.setQuestion(question);
        questionObject.setQuestionNumber(++questionNumberCounter);
        Question savedQuestion = questionRepository.save(questionObject);
        return QuestionDto.builder()
                .questionNumber(savedQuestion.getQuestionNumber())
                .question(savedQuestion.getQuestion())
                .build();
    }

    @Override
    public List<Question> loadAllQuestions() {
        return questionRepository.findAll();
    }
}

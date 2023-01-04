package com.twinkles.talkamout.repository;

import com.twinkles.talkamout.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findQuestionByQuestionNumber(int questionNumber);
    boolean existsByQuestion(String question);
}

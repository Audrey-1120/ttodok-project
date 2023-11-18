package com.suyuri.ttodokproject.repository.quiz;

import com.suyuri.ttodokproject.entity.quiz.QuizQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<QuizQuestionEntity, String> {
    List<QuizQuestionEntity> findByQuizCode(String card);


}
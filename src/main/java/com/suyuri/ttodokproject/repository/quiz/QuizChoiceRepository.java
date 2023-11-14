package com.suyuri.ttodokproject.repository.quiz;

import com.suyuri.ttodokproject.entity.quiz.QuizChoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizChoiceRepository extends JpaRepository<QuizChoiceEntity, String> {

    List<QuizChoiceEntity> findByQuestionCode(String card);
}
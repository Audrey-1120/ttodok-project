package com.suyuri.ttodokproject.repository.quiz;

import com.suyuri.ttodokproject.entity.quiz.QuizAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAnswerRepository extends JpaRepository<QuizAnswerEntity, String> {

    List<QuizAnswerEntity> findByQuestionCode(String questionCode);
}
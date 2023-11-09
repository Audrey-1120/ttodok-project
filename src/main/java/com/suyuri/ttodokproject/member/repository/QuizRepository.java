package com.suyuri.ttodokproject.member.repository;


import com.suyuri.ttodokproject.member.entity.QuizQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<QuizQuestionEntity, String>{

    List<QuizQuestionEntity> findByQuizCode(String card);
}

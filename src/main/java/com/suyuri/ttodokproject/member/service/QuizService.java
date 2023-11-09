package com.suyuri.ttodokproject.member.service;

import com.suyuri.ttodokproject.member.entity.QuizQuestionEntity;
import com.suyuri.ttodokproject.member.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    @Autowired
    public static QuizRepository quizRepository;

    public static List<QuizQuestionEntity> getQuizQuestionByQuizCode(String card) {
        List<QuizQuestionEntity> quizQuestionEntities = quizRepository.findByQuizCode(card);
        return quizQuestionEntities;
    }
}

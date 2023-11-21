package com.suyuri.ttodokproject.service.quiz;


import com.suyuri.ttodokproject.entity.quiz.QuizAnswerEntity;
import com.suyuri.ttodokproject.entity.quiz.QuizChoiceEntity;
import com.suyuri.ttodokproject.repository.quiz.QuizAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizAnswerService {


    @Autowired
    private final QuizAnswerRepository quizAnswerRepository;

    //퀴즈  - 해설
    //해당 퀴즈 번호에 해당하는 해설 값 레포지토리 통해서 가져오기
    public List<QuizAnswerEntity> getQuizAnswerByQuestionCode(String questionCode) {
        List<QuizAnswerEntity> quizAnswerEntities = quizAnswerRepository.findByQuestionCode(questionCode);
        return quizAnswerEntities;
    }

}
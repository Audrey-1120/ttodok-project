package com.suyuri.ttodokproject.controller.quiz;

import com.suyuri.ttodokproject.entity.quiz.QuizAnswerEntity;
import com.suyuri.ttodokproject.service.MemberService;
import com.suyuri.ttodokproject.service.quiz.QuizAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class QuizAnswerController {

    @Autowired
    private final QuizAnswerService quizAnswerService;

    @Autowired
    private final MemberService memberService;


    //해설 띄워주기
    @PostMapping("/submitAnswer")
    @ResponseBody
    public Map<String, Object> submitAnswer(@RequestParam("questionCode") String questionCode) {
        List<QuizAnswerEntity> quizAnswerEntities = quizAnswerService.getQuizAnswerByQuestionCode(questionCode);
        Map<String, Object> response = new HashMap<>();
        if(!quizAnswerEntities.isEmpty()){
            response.put("data", quizAnswerEntities);
        } else {
            response.put("data", new ArrayList<>());
        }
        return response;
    }





}
package com.suyuri.ttodokproject.controller.quiz;


import com.suyuri.ttodokproject.entity.quiz.QuizChoiceEntity;
import com.suyuri.ttodokproject.service.quiz.QuizChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class QuizChoiceController {

    @Autowired
    private QuizChoiceService quizChoiceService;

    //선택지 데이터 불러오기
    @GetMapping("/study/quizChoice")
    @ResponseBody
    public Map<String, Object> quizChoiceForm(@RequestParam String card) {
        List<QuizChoiceEntity> quizChoiceEntities = quizChoiceService.getQuizChoiceByChoiceCode(card);
        Map<String, Object> response = new HashMap<>();
        if(!quizChoiceEntities.isEmpty()){
            response.put("data", quizChoiceEntities);
        } else {
            response.put("data", new ArrayList<>());
        }
        return response;
    }

}
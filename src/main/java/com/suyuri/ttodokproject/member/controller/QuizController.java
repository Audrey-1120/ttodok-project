package com.suyuri.ttodokproject.member.controller;

import com.suyuri.ttodokproject.member.entity.QuizQuestionEntity;
import com.suyuri.ttodokproject.member.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class QuizController {


    @Autowired
    private final QuizService quizService;

    //퀴즈 홈 페이지 출력 요청
    @GetMapping("/quizhome")
    public String quizhomeform() {
        return "quizhome";
    }

    //퀴즈1 페이지 출력 요청
    @GetMapping("/quiz1")
    public String quiz1form() {
        return "quiz1";
    }

//    @GetMapping("/study/quiz")
//    @ResponseBody
//    public Map<String, Object> quizForm(@RequestParam String card) {
//        System.out.println("퀴즈 값 가져와 졌나요?" + card); //테스트용 값 찍어보기
//        List<QuizQuestionEntity> quizQuestionEntities = QuizService.getQuizQuestionByQuizCode(card);
//        Map<String, Object> response = new HashMap<>();
//        if (!quizQuestionEntities.isEmpty()) {
//            response.put("data", quizQuestionEntities);
//            response.put("count", quizQuestionEntities.size());
//        } else { //리스트가 비어있을 때
//            response.put("data", new ArrayList<>());
//            response.put("count", 0);
//        }
//        return response;
//    }

    @GetMapping("/study/quiz")
    public String quizForm(@RequestParam String card, Model model) {
        System.out.println("퀴즈 값 가져와 졌나요?" + card); //테스트용 값 찍어보기
        List<QuizQuestionEntity> quizQuestionEntities = QuizService.getQuizQuestionByQuizCode(card);
        if (!quizQuestionEntities.isEmpty()) {
            QuizQuestionEntity quizQuestionEntity = quizQuestionEntities.get(0);
            model.addAttribute("questionContent", quizQuestionEntity.getQuestionContent());
            model.addAttribute("questionPassage", quizQuestionEntity.getQuestionPassage());


        } else { //리스트가 비어있을 때

            model.addAttribute("questionContent", "error");
            model.addAttribute("questionPassage", "error");
        }
        return "quiz1";
    }

}

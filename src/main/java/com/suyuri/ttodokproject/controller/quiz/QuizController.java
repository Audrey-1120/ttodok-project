package com.suyuri.ttodokproject.controller.quiz;

import com.suyuri.ttodokproject.dto.MemberDTO;
import com.suyuri.ttodokproject.entity.quiz.QuizQuestionEntity;
import com.suyuri.ttodokproject.service.MemberService;
import com.suyuri.ttodokproject.service.quiz.QuizService;
import jakarta.servlet.http.HttpSession;
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
public class QuizController {


    @Autowired
    private QuizService quizService;

    @Autowired
    private MemberService memberService;


    //퀴즈 홈 페이지 출력 요청
    @GetMapping("/quizhome")
    public String quizhomeform(HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        return "quizhome";
    }



    //퀴즈1 페이지 출력 요청
    @GetMapping("/quiz1")
    public String quiz1form(HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        return "quiz1";
    }

    //퀴즈2 페이지 출력 요청
    @GetMapping("/quiz2")
    public String quiz2form(HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        return "quiz2";
    }

    //퀴즈3 페이지 출력 요청
    @GetMapping("/quiz3")
    public String quiz3form(HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        return "quiz3";
    }

    //퀴즈4 페이지 출력 요청
    @GetMapping("/quiz4")
    public String quiz4form(HttpSession session) {
        String memberId = (String) session.getAttribute("loginId");
        return "quiz4";
    }


    //퀴즈 문제 & 지문 가져오기.
    @GetMapping("/study/quizQuestion")
    @ResponseBody
    public Map<String, Object> quizQuestionForm(@RequestParam String card) {
        System.out.println("card: " + card); //테스트용 값 찍어보기
        List<QuizQuestionEntity> quizQuestionEntities = quizService.getQuizQuestionByQuizCode(card); //List<WordEntity> 형태로 WordService의 getWordByWordCode 메소드를 이용해 card에 해당하는 값 받아옴.
        Map<String, Object> response = new HashMap<>(); //비어있는 응답 맵인 response 생성
        if (!quizQuestionEntities.isEmpty()) { //리스트가 비어있는지 확인 - 비어있지 않을 때
            response.put("data", quizQuestionEntities); //response 맵에 data 키로 wordEntities 넣음.
        } else { //리스트가 비어있을 때
            response.put("data", new ArrayList<>()); //빈 ArrayList를 data키로
        }
        return response; //해당 response 맵을 반환해서 JSON응답을 클라이언트에게 보냄.
    }


    //퀴즈 - 포인트 100점 주기 (1)
    //클라이언트로 로그인한 사용자 아이디와 그 유저의 포인트 값 전송.
    @PostMapping("/quiz4/pointadd")
    public @ResponseBody Map<String, Object> pointadd(HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        String loginId = (String) session.getAttribute("loginId");
        int memberPoint = memberService.getMemberPoint(loginId);

        response.put("loginId", loginId);
        response.put("memberPoint", memberPoint);

        System.out.println("loginId: " + loginId);
        System.out.println("memberpoint: " + memberPoint);

        return response;
    }

    //퀴즈 - 포인트 100점 주기 (2)
    //클라이언트로부터 변경된 포인트 값과 해당 유저 아이디 받음.

    @PostMapping("/quiz4/updatepoint")
    public String updatePoint(@RequestParam String loginId,
                              @RequestParam int point) {
        System.out.println("loginId : " + loginId);

        MemberDTO memberDTO = new MemberDTO(loginId, point);
        memberService.updateMemberPoint(memberDTO);
        return "quiz4";
    }






}
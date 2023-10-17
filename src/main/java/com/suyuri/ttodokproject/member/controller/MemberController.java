package com.suyuri.ttodokproject.member.controller;

import com.suyuri.ttodokproject.member.dto.MemberDTO;
import com.suyuri.ttodokproject.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {


    //생성자 주입 - 필드를 매개변수로 하는 생성자를 만들어줌. 자동적으로 서비스에 대한 객체를 주입받는다!!
    private final MemberService memberService;


    //여기부터 회원가입 시작
    // 회원가입 페이지 출력 요청
    @GetMapping("/member/join")
    public String joinForm() {
        return "join";
    }

    //회원가입 폼에서 데이터를 받아서 서비스의 save객체에 파라미터로 넘겨준다.
    @PostMapping("/member/join")
    public String save(@ModelAttribute MemberDTO memberDTO) {

        LocalDate birthDate = LocalDate.of(memberDTO.getYear(), memberDTO.getMonth(), memberDTO.getDay());
        //년, 월, 일 값을 사용하여 LocalDate 객체 생성함. 이를 birthDate변수에 할당함.
        memberDTO.setBirthDate(birthDate);
        //setBirthDate 메소드를 사용하여 DTO객체에 birthDate 값 설정함.
        memberService.save(memberDTO);
        return "login";
    }
    //회원가입 끝



    //여기부터 로그인 시작

    //로그인 페이지 띄워줌
    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }


    //로그인 처리 API
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        Map<String, String> loginResult = memberService.login(memberDTO);
        if (loginResult.get("status").equals("success")) {
            session.setAttribute("loginId", memberDTO.getMemberId());
            return "main";
        } else {
            String errorMessage = loginResult.get("message");
            if (errorMessage != null) {
                model.addAttribute("errorMessage", errorMessage);
            }
            return "login";
        } //아이디가 틀리면 아이디가 틀렸다고 에러메시지 넣어줌.
    }

    //아이디 중복체크
    @PostMapping("/member/id-check")
    public @ResponseBody String idCheck(@RequestParam("memberId") String memberId) {
        System.out.println("memberId = " + memberId); //파라미터가 제대로 오는지 확인
        String checkResult = memberService.idCheck(memberId);
        return checkResult;

//        if(checkResult != null) {
//            return "ok";
//        } else {
//            return "no";
//        }


        //로그인 끝
    }
}
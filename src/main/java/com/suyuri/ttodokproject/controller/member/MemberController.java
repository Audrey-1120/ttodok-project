package com.suyuri.ttodokproject.controller.member;

import com.suyuri.ttodokproject.dto.MemberDTO;
import com.suyuri.ttodokproject.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


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
        return "endjoin";
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

            return "redirect:/main_ver2"; // 리다이렉트 사용
        } else {
            String errorMessage = loginResult.get("message");
            if (errorMessage != null) {
                model.addAttribute("errorMessage", errorMessage);
            }
            return "login";
        } //아이디가 틀리면 아이디가 틀렸다고 에러메시지 넣어줌.
    }


    //아이디 중복확인
    @PostMapping("/member/id-check")
    public @ResponseBody String idCheck(@RequestParam("memberId") String memberId) {
        System.out.println("memberId = " + memberId); //파라미터가 제대로 오는지 확인
        String checkResult = memberService.idCheck(memberId);
        return checkResult;

        //로그인 끝
    }

    @PostMapping("/member/pw-check")
    public @ResponseBody String passwordCheck(@RequestParam("memberPw") String memberPw) {
        System.out.println("memberPw = " + memberPw); //파라미터가 제대로 오는지 확인
        String pwcheckResult = memberService.passwordCheck(memberPw);
        return pwcheckResult;
    }


    //-짐

    // 회원탈퇴

    @DeleteMapping("/members/delete")
    public ResponseEntity<String> deleteMember(HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");

        if (loginId != null) {
            // 회원 탈퇴 로직 수행
            boolean success = memberService.deleteMember(loginId);

            if (success) {
                session.invalidate(); // 세션 무효화
                return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("회원 탈퇴에 실패했습니다.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("로그인된 사용자만 회원 탈퇴가 가능합니다.");
        }
    }


    // 메인 페이지와 마이페이지 출력 요청

    @GetMapping("/main_ver2")
    public String mainPage(HttpSession session, Model model) {
        String loginId = (String) session.getAttribute("loginId");
        System.out.println("loginId: " + loginId);

        if (loginId != null) {
            String userNick = memberService.getNickName(loginId);
            model.addAttribute("userNick", userNick);
            int memberPoint = memberService.getMemberPoint(loginId);
            model.addAttribute("memberPoint", memberPoint);
        }

        return "main_ver2";
    }

    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model model) {
        String loginId = (String) session.getAttribute("loginId");

        if (loginId != null) {
            String userNick = memberService.getNickName(loginId);
            model.addAttribute("userNick", userNick);
            int memberPoint = memberService.getMemberPoint(loginId);
            model.addAttribute("memberPoint", memberPoint);
        }

        return "myPage";
    }



    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate(); // 세션 무효화
        return "redirect:/member/login"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }
}
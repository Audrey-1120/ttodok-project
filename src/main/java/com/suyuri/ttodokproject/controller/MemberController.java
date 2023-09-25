package com.suyuri.ttodokproject.controller;

import com.suyuri.ttodokproject.dto.MemberDTO;
import com.suyuri.ttodokproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입 페이지 출력
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save") //name 값을 requestparam에 담아옴. 회원정보 저장
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberEmail = " + memberDTO);
        memberService.save(memberDTO);

        return "index";

    }


}

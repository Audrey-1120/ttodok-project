package com.suyuri.ttodokproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TextController {

    //글 학습 페이지 출력 요청
    @GetMapping("/textstudy")
    public String textstudyForm() {
        return "textstudy";
    }
}

package com.suyuri.ttodokproject.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController { //전체 요청을 처리하는 대표 컨트롤러

    //기본 페이지 요청 메소드
    @GetMapping("main_ver2.html")
    public String index() { //인덱스 페이지를 요청하는 API
        return "index";
    }
}

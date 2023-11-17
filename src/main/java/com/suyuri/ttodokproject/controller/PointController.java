package com.suyuri.ttodokproject.controller;

import com.suyuri.ttodokproject.service.MemberService;
import com.suyuri.ttodokproject.service.point.PointService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
public class PointController {

    private final PointService pointService;

    private final MemberService memberService;

    //포인트상점 페이지 출력
    @GetMapping("/pointshop")
    public String pointshop(HttpSession session, Model model) {
        String loginId = (String) session.getAttribute("loginId");
        System.out.println("loginId: " + loginId);
        List<String> allProductNames = pointService.getAllProductNames();
        System.out.println("allProductNames: " + allProductNames);
        List<Integer> allProductPoints = pointService.getAllProductPoints();
        System.out.println("allProductPoints: " + allProductPoints);
        if (loginId != null) {
            int memberPoint = memberService.getMemberPoint(loginId);
            model.addAttribute("memberPoint", memberPoint);
            model.addAttribute("productName", allProductNames);
            model.addAttribute("productPoint", allProductPoints);
        }

        return "pointshop_home";
    }


    //포인트상점 데이터 가져오기
//    @GetMapping("/pointshop/stuff")
//    public String pointshopgetstuff(HttpSession session, Model model) {
//        List<String> allProductNames = pointService.getAllProductNames();
//        System.out.println("allProductNames: " + allProductNames);
//
//        model.addAttribute("productName", allProductNames);
//
//        List<Integer> allProductPoints = pointService.getAllProductPoints();
//        System.out.println("allProductPoints: " + allProductPoints);
//        model.addAttribute("productPoint", allProductPoints);
//
//        return "pointshop_home";
//    }
}
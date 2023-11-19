package com.suyuri.ttodokproject.controller.point;

import com.suyuri.ttodokproject.dto.MemberDTO;
import com.suyuri.ttodokproject.service.MemberService;
import com.suyuri.ttodokproject.service.point.PointService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    //포인트상점 - 구매하기 전에 사용자 아이디와 사용자의 현 포인트 값 클라이언트로 전송.
    @PostMapping("/pointshop/buyproduct")
    public @ResponseBody Map<String, Object> buyproduct(HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        String loginId = (String) session.getAttribute("loginId");
        int memberPoint = memberService.getMemberPoint(loginId);

        response.put("loginId", loginId);
        response.put("memberPoint", memberPoint);

        System.out.println("loginId: " + loginId);
        System.out.println("memberPoint: "+ memberPoint);

        return response;
    }


    //구매 후 차감한 포인트 값 클라이언트로부터 받음
    @PostMapping("/pointshop/productupdatepoint")
    public String pointhomeupdatepoint(@RequestParam String loginId,
                                       @RequestParam int point) {
        System.out.println("loginId: " + loginId);
        System.out.println("updatedProductPoint: " + point);

        MemberDTO memberDTO = new MemberDTO(loginId, point);
        memberService.updateProductMemberPoint(memberDTO);
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
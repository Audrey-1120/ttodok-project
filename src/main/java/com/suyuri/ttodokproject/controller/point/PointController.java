package com.suyuri.ttodokproject.controller.point;

import com.suyuri.ttodokproject.dto.MemberDTO;
import com.suyuri.ttodokproject.entity.PointEntity;
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
        List<String> allProductNames = pointService.getAllProductNames();
        List<Integer> allProductPoints = pointService.getAllProductPoints();
        List<String> allProductCodes = pointService.getAllProductCodes();


        if (loginId != null) {
            int memberPoint = memberService.getMemberPoint(loginId);
            model.addAttribute("memberPoint", memberPoint);
            model.addAttribute("productName", allProductNames);
            model.addAttribute("productPoint", allProductPoints);
            model.addAttribute("productCode", allProductCodes);
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

        return response;
    }


    //구매 후 차감한 포인트 값 클라이언트로부터 받음
    @PostMapping("/pointshop/productupdatepoint")
    public String pointhomeupdatepoint(@RequestParam String loginId,
                                       @RequestParam int point) {

        MemberDTO memberDTO = new MemberDTO(loginId, point);
        memberService.updateProductMemberPoint(memberDTO);
        return "pointshop_home";
    }




    //포인트 구매 후 구매 상품 페이지 출력 - point.js
    @GetMapping("/pointshopresult")
    public String pointshopresult(@RequestParam("currentUser") String currentUser,
                                  @RequestParam("selectProductCode") String productCode) {

        return "pointshop_result";
    }



    @PostMapping("/pointshopresult/gifticonimg")
    @ResponseBody
    public Map<String, Object> updateProductCodeOnServer(@RequestParam("loginId") String loginId,
                                                         @RequestParam("productCode") String productCode) {
        Map<String, Object> response = new HashMap<>();

        // productCode에 해당하는 productName과 productImage 리스트 가져오기
        List<PointEntity> pointEntityList = pointService.getProductInfo(productCode);

        // 사용자 포인트 가져오기
        int memberPoint = memberService.getMemberPoint(loginId);

        response.put("pointEntityList", pointEntityList);
        response.put("memberPoint", memberPoint);

        return response;
    }




}
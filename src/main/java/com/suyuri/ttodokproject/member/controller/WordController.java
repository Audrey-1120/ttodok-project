package com.suyuri.ttodokproject.member.controller;

import com.suyuri.ttodokproject.member.entity.WordEntity;
import com.suyuri.ttodokproject.member.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    //단어학습 페이지 출력 요청
    @GetMapping("/wordstudy")
    public String wordstudyForm() {
        return "wordstudy";
    }

    @GetMapping("/study/wordstudy")
    @ResponseBody
    public Map<String, Object> wordstudyForm(@RequestParam String card) {
        System.out.println("card: " + card); //테스트용 값 찍어보기
        List<WordEntity> wordEntities = wordService.getWordByWordCode(card); //List<WordEntity> 형태로 WordService의 getWordByWordCode 메소드를 이용해 card에 해당하는 값 받아옴.
        Map<String, Object> response = new HashMap<>(); //비어있는 응답 맵인 response 생성
        if (!wordEntities.isEmpty()) { //리스트가 비어있는지 확인 - 비어있지 않을 때
            response.put("data", wordEntities); //response 맵에 data 키로 wordEntities 넣음.
            response.put("count", wordEntities.size()); //response 맵에 count 키로 wordEntities.size() 넣음. - 슬라이드 동적 생성해야 해서 데이터 개수도 같이 보냄.
        } else { //리스트가 비어있을 때
            response.put("data", new ArrayList<>()); //빈 ArrayList를 data키로
            response.put("count", 0); //0을 count키로 보냄.
        }
        return response; //해당 response 맵을 반환해서 JSON응답을 클라이언트에게 보냄.
    }

}
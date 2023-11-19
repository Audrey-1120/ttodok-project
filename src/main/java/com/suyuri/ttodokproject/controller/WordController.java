package com.suyuri.ttodokproject.controller;

import ch.qos.logback.core.model.Model;
import com.suyuri.ttodokproject.entity.TextEntity;
import com.suyuri.ttodokproject.entity.WordEntity;
import com.suyuri.ttodokproject.service.TextService;
import com.suyuri.ttodokproject.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    private final TextService textService;

    //단어학습 페이지 출력 요청
    @GetMapping("/wordstudy")
    public String wordstudyForm() {
        return "wordstudy";
    }

    //슬라이드 데이터 가져오기
//    @GetMapping("/study/wordstudy")
//    @ResponseBody
//    public Map<String, Object> wordstudyForm(@RequestParam String card) {
//        System.out.println("card: " + card); //테스트용 값 찍어보기
//        List<WordEntity> wordEntities = wordService.getWordByWordCode(card); //List<WordEntity> 형태로 WordService의 getWordByWordCode 메소드를 이용해 card에 해당하는 값 받아옴.
//        Map<String, Object> response = new HashMap<>(); //비어있는 응답 맵인 response 생성
//        if (!wordEntities.isEmpty()) { //리스트가 비어있는지 확인 - 비어있지 않을 때
//            response.put("data", wordEntities); //response 맵에 data 키로 wordEntities 넣음.
//            response.put("count", wordEntities.size()); //response 맵에 count 키로 wordEntities.size() 넣음. - 슬라이드 동적 생성해야 해서 데이터 개수도 같이 보냄.
//        } else { //리스트가 비어있을 때
//            response.put("data", new ArrayList<>()); //빈 ArrayList를 data키로
//            response.put("count", 0); //0을 count키로 보냄.
//        }
//        return response; //해당 response 맵을 반환해서 JSON응답을 클라이언트에게 보냄.
//    }

    @GetMapping("/study/wordstudy")
    @ResponseBody
    public Map<String, Object> wordstudyForm(@RequestParam String card) {
        System.out.println("card: " + card);

        List<WordEntity> wordEntities = wordService.getWordByWordCode(card);
        List<TextEntity> textEntities = textService.getTextByCard(card);

        Map<String, Object> response = new HashMap<>();

        if (!wordEntities.isEmpty()) {
            WordEntity wordEntity = wordEntities.get(0); // 가정: 리스트에서 첫 번째 원소를 사용

            // WordEntity에서 TextEntity로의 참조를 따라가서 TextTitle 값을 가져옴
            response.put("textTitle", !textEntities.isEmpty() ? textEntities.get(0).getTextTitle() : null);
            response.put("data", wordEntities);
            response.put("count", wordEntities.size());
        } else {
            response.put("textTitle", null); // 또는 다른 값을 설정할 수 있음
            response.put("data", new ArrayList<>());
            response.put("count", 0);
        }

        return response;
    }




    //해당 동화 제목 가져오기
//    @GetMapping("/study/wordtitle")
//    public String wordtitle(@RequestParam String card, Model model) {
//
//
//        List<TextEntity> textEntities = textService.getTextByCode(card);
//        if(textEntities != null) {
//            model.addAttribute("wordTitle");
//        } else {
//            model.addAttribute("wordTitle", "error");
//        }
//
//    }



}
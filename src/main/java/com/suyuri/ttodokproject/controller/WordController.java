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



}
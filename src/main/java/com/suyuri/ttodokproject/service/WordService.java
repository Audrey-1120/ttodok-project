package com.suyuri.ttodokproject.service;

import com.suyuri.ttodokproject.entity.WordEntity;
import com.suyuri.ttodokproject.repository.TextRepository;
import com.suyuri.ttodokproject.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;
    private final TextRepository textRepository;


    //단어 슬라이드 데이터 가져오는 메소드
    public List<WordEntity> getWordByWordCode(String card) {
        List<WordEntity> wordEntities = wordRepository.findByWordCode(card);
        return wordEntities;
    }

}

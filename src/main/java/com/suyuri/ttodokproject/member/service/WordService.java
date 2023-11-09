package com.suyuri.ttodokproject.member.service;

import com.suyuri.ttodokproject.member.entity.WordEntity;
import com.suyuri.ttodokproject.member.repository.TextRepository;
import com.suyuri.ttodokproject.member.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


//    //단어 해당하는 동화 제목 가져오는 메소드
//    public WordEntity getWordTitleByWordCode(String card) {
//        WordEntity wordEntity = textRepository.findByTextCode(card);
//        return wordEntity;
//    }
}

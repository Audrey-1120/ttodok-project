package com.suyuri.ttodokproject.member.service;

import com.suyuri.ttodokproject.member.entity.WordEntity;
import com.suyuri.ttodokproject.member.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;


    //getWordByWordCode 메소드
    public List<WordEntity> getWordByWordCode(String card) {
        List<WordEntity> wordEntities = wordRepository.findByWordCode(card);
        return wordEntities;
    }


}

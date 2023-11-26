package com.suyuri.ttodokproject.service;

import com.suyuri.ttodokproject.entity.TdWordEntity;
import com.suyuri.ttodokproject.entity.WordEntity;
import com.suyuri.ttodokproject.repository.MemberRepository;
import com.suyuri.ttodokproject.repository.TdWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TdWordService {

    private final TdWordRepository tdWordRepository;


    public TdWordEntity getRandomWord() {
        List<TdWordEntity> wordList = tdWordRepository.findAll();
        int randomIndex = new Random().nextInt(wordList.size());

        return wordList.get(randomIndex);
    }


}
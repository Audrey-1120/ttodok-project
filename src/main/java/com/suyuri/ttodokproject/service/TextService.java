package com.suyuri.ttodokproject.service;

import com.suyuri.ttodokproject.entity.TextEntity;
import com.suyuri.ttodokproject.entity.WordEntity;
import com.suyuri.ttodokproject.repository.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TextService {

    @Autowired
    private final TextRepository textRepository;
    //public TextService(TextRepository textRepository) {
    //    this.textRepository = textRepository;
    //}

    public List<TextEntity> getTextByCard(String card) {
        List<TextEntity> textEntities = textRepository.findByTextCode(card);

        return textEntities;
    }

}

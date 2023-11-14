package com.suyuri.ttodokproject.repository;

import com.suyuri.ttodokproject.entity.TextEntity;
import com.suyuri.ttodokproject.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<TextEntity, String> {

    //해당 동화 번호 받아서 값
    WordEntity findByTextCode(String card);
}

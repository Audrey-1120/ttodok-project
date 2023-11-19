package com.suyuri.ttodokproject.repository;

import com.suyuri.ttodokproject.entity.TextEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TextRepository extends JpaRepository<TextEntity, String> {

//    List<TextEntity> findByTextCode(String card);

    public List<TextEntity> findByTextCode(String textCode);
}
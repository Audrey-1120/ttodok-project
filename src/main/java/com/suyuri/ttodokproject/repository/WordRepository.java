package com.suyuri.ttodokproject.repository;

import com.suyuri.ttodokproject.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<WordEntity, String> {

    List<WordEntity> findByWordCode(String card);
}
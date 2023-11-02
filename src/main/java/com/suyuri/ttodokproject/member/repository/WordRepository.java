package com.suyuri.ttodokproject.member.repository;

import com.suyuri.ttodokproject.member.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<WordEntity, String> {

    List<WordEntity> findByWordCode(String card);
}

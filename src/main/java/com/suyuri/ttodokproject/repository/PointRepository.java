package com.suyuri.ttodokproject.repository;

import com.suyuri.ttodokproject.entity.MemberEntity;
import com.suyuri.ttodokproject.entity.PointEntity;
import com.suyuri.ttodokproject.entity.TextEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<PointEntity, String> {

}
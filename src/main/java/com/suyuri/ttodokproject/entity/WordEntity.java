package com.suyuri.ttodokproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tbl_word")
public class WordEntity {

    @Id
    private String word;

    @Column(unique = true, length = 10, nullable = false) //겹치는값들어가는지, 길이, 널이가능한가
    private String wordCode;

    @Column(length = 100, nullable = false)
    private String wordMean;

    @Column(length = 100, nullable = false)
    private String wordEx;
}
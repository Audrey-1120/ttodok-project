package com.suyuri.ttodokproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_text")
public class TextEntity {

    @Id
    private String textCode;

    @Column(length = 50, nullable = false)
    private String textTitle;

    @Column(nullable = false)
    private String textContent;

    @Column(nullable = false)
    private int textLevel;
}
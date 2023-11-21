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
    private String text;

    @Column(unique = true, length = 10, nullable = false)
    private String textCode;

    @Column(length = 100, nullable = false)
    private String textTitle;

    @Column(length = 1000, nullable = false)
    private String textContent;

    @Column(length = 100, nullable = false)
    private int textLevel;

}

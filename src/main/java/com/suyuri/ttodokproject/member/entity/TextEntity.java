package com.suyuri.ttodokproject.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tbl_text")
public class TextEntity {

    @Id
    private String textCode;

    @Column
    private String textTitle;

    @Column
    private String textContent;

    @Column
    private Integer textLevel;
}

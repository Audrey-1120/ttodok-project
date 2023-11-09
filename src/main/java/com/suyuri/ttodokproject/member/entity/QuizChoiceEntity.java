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
@Table(name = "tbl_choice")
public class QuizChoiceEntity {

    @Id
    private String choiceCode;

    @Column
    private String choiceContent01;

    @Column
    private String choiceContent02;

    @Column
    private String choiceContent03;

    @Column
    private String choiceContent04;

}

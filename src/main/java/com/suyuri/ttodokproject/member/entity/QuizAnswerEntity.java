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
@Table(name = "tbl_answer")
public class QuizAnswerEntity {

    @Id
    private String answerCode;

    @Column
    private String answerContent01;

    @Column
    private String answerContent02;

    @Column
    private String answerContent03;

    @Column
    private String answerContent04;


}

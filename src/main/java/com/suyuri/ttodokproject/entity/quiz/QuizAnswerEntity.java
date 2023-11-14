package com.suyuri.ttodokproject.entity.quiz;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tbl_answer")
public class QuizAnswerEntity {

    @Id //pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(length = 10, nullable = false)
    private String answerCode;

    @Column(length = 70, nullable = false)
    private String answerContent;

    @Column(length = 10, nullable = false)
    private String questionCode;



}
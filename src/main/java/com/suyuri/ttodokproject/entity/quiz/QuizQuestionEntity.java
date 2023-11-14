package com.suyuri.ttodokproject.entity.quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tbl_question")
public class QuizQuestionEntity {

    @Id
    private String questionCode;

    @Column(length = 10, nullable = false)
    private String quizCode;

    @Column(length = 70, nullable = false)
    private String questionContent;

    @Column(length = 100)
    private String questionPassage;

}
package com.suyuri.ttodokproject.entity.quiz;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tbl_choice")
public class QuizChoiceEntity {


    @Id //pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(length = 10, nullable = false)
    private String choiceCode;

    @Column(length = 20, nullable = false)
    private String choiceContent;

    @Column(nullable = false)
    private Integer isAnswerRight;

    @Column(length = 10, nullable = false)
    private String questionCode;

}
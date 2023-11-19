package com.suyuri.ttodokproject.entity.main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "tbl_tdword")
public class TdWordEntity {

    @Id
    private String tdword_code;
    @Column
    private String tdword;
    @Column
    private String tdword_mean;
    @Column
    private String tdword_ex;

    // 생성자, 게터, 세터 등 필요한 메서드 추가
}

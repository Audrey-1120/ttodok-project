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
@Table(name = "tbl_product")
public class PointEntity {

    @Id
    private String productCode;

    @Column(length = 20, nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer productPoint;

    @Column(nullable = false)
    private String productImage;
}
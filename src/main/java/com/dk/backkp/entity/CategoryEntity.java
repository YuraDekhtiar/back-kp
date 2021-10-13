package com.dk.backkp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "category_table")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}

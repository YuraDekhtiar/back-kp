package com.dk.backkp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tags_table")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;

    public TagEntity() {}
    public TagEntity(String t) {
        this.tag = t;
    }


}

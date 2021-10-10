package com.dk.backkp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "img_table")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String publicId;


    @ManyToOne
    @JoinColumn(name = "task_id")
    private MyTaskEntity task;
}
package com.dk.backkp.entity;

import com.dk.backkp.model.MyTask;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "img_url_table")
public class ImgUrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private MyTaskEntity task;
}
package com.dk.backkp.entity;

import com.dk.backkp.model.MyTask;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "img_table")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String public_id;
    private String url;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private MyTaskEntity task;
}
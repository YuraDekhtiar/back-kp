package com.dk.backkp.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "answers_table")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private MyTaskEntity task;

}
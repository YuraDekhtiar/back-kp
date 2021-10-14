package com.dk.backkp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_answer_table")
public class UserAnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;
    @OneToOne
    @JoinColumn(name = "task_id")
    MyTaskEntity task;
}

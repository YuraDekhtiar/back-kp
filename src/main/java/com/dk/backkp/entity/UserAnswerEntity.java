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
    UserEntity user;
    @OneToOne
    MyTaskEntity task;
}

package com.dk.backkp.dto;


import com.dk.backkp.entity.UserEntity;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private String registered;
    private int taskCreatedCount;
    private int taskCompletedCount;

    public User(UserEntity userEntity, int taskCreatedCount, int taskCompletedCount) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.imageUrl = userEntity.getImageUrl();
        this.registered = userEntity.getRegistered().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.taskCreatedCount = taskCreatedCount;
        this.taskCompletedCount = taskCompletedCount;
    }
}


package com.dk.backkp.dto;

import com.dk.backkp.entity.UserEntity;
import lombok.Data;

@Data
public class Author {
    private long id;
    private String name;
    private String email;
    private String imageUrl;

    public static Author toModel(UserEntity user) {
        Author model = new Author();

        model.setId(user.getId());
        model.setName(user.getName());
        model.setEmail(user.getEmail());
        model.setImageUrl(user.getImageUrl());

        return model;
    }
}

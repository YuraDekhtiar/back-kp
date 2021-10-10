package com.dk.backkp.dto;

import com.dk.backkp.entity.ImageEntity;
import lombok.Data;

@Data
public class Image {
    private String url;
    private String publicId;

    public Image(String url, String publicId) {
        this.url = url;
        this.publicId = publicId;
    }

    public Image() { }

    public static Image toModel(ImageEntity imageEntity) {
        Image model = new Image();
        model.setUrl(imageEntity.getUrl());
        model.setPublicId(imageEntity.getPublicId());

        return model;
    }
}

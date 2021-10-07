package com.dk.backkp.model;

import com.dk.backkp.entity.ImageEntity;
import lombok.Data;

@Data
public class Images {
    private String url;
    private String publicId;

    public Images(String url, String publicId) {
        this.url = url;
        this.publicId = publicId;
    }

    public Images() { }

    public static Images toModel(ImageEntity imageEntity) {
        Images model = new Images();
        model.setUrl(imageEntity.getUrl());
        model.setPublicId(imageEntity.getPublic_id());

        return model;
    }
}

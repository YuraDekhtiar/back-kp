package com.dk.backkp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dk.backkp.dto.Image;
import com.dk.backkp.entity.ImageEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class FileService {
    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "difj1jveu",
            "api_key", "772269676423848",
            "api_secret", "qjpotWHhHI7AMrbDqMexqlnGS80"));

    public List<Image> upload(MultipartFile[] files) throws Exception {
        List <Image> images = new ArrayList<>();

       for (MultipartFile file: files) {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("folder", "task_img"));
           images.add(new Image(uploadResult.get("url").toString(), uploadResult.get("public_id").toString()));
        }

        return images;
    }

    public boolean delete(List<ImageEntity> images) throws Exception {
        List<String> publicId = new ArrayList<>();
        for (ImageEntity image:images) {
            publicId.add(image.getPublicId());
        }
        if(publicId.size() > 0)
            cloudinary.api().deleteResources(publicId, ObjectUtils.emptyMap() );
        return true;
    }

}


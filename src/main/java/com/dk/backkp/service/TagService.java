package com.dk.backkp.service;

import com.dk.backkp.entity.TagEntity;
import com.dk.backkp.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public void add(String tags) {
        for (String word:convert(tags))
            if( tagRepository.findByTag(word) == null) {
                tagRepository.save(new TagEntity(word));
            }
    }

    public String[] convert(String tags) {
        return tags.split("\\s+");
    }
}

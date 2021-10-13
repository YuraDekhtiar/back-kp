package com.dk.backkp.repository;

import com.dk.backkp.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    TagEntity findByTag(String tag);
}

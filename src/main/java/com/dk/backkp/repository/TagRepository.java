package com.dk.backkp.repository;

import com.dk.backkp.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
    TagEntity findByTag(String tag);
}

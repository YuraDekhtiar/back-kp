package com.dk.backkp.repository;

import com.dk.backkp.entity.AnswerEntity;
import com.dk.backkp.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}

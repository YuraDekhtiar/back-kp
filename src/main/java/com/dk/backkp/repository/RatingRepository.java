package com.dk.backkp.repository;

import com.dk.backkp.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    Optional<RatingEntity> findByTask_idAndUser_id(Long taskId, Long userId);
    List <RatingEntity> findAllByTask_id(Long taskId);
}

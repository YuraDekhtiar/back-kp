package com.dk.backkp.repository;

import com.dk.backkp.entity.UserAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswerEntity, Long> {
    Optional<UserAnswerEntity> findByUser_idAndTask_id(Long userId, Long taskId);
    List<UserAnswerEntity> findAllByUser_id(Long id);
}
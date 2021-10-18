package com.dk.backkp.repository;

import com.dk.backkp.entity.MyTaskEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<MyTaskEntity, Long> {
    List <MyTaskEntity> findAllByAuthor_id(Long id);
}

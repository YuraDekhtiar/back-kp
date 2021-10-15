package com.dk.backkp.repository;

import com.dk.backkp.dto.MyTask;
import com.dk.backkp.entity.MyTaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<MyTaskEntity, Long> {
    List <MyTaskEntity> findAllByAuthor_id(Long id);
}

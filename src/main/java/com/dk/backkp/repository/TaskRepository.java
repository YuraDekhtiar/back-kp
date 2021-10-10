package com.dk.backkp.repository;

import com.dk.backkp.entity.MyTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TaskRepository extends JpaRepository<MyTaskEntity, Long> {

}

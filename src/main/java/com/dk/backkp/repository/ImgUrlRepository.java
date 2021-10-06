package com.dk.backkp.repository;

import com.dk.backkp.entity.MyTaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgUrlRepository extends CrudRepository<MyTaskEntity, Long> {
}

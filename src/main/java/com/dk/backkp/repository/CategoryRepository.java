package com.dk.backkp.repository;

import com.dk.backkp.entity.CategoryEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAll(Sort sort);
}
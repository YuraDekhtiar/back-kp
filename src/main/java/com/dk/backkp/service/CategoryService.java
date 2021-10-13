package com.dk.backkp.service;

import com.dk.backkp.entity.CategoryEntity;
import com.dk.backkp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryEntity> getAllCategory() {
        return categoryRepository.findAll(Sort.by("name"));
    }
}

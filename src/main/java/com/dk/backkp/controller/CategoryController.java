package com.dk.backkp.controller;

import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.security.CurrentUser;
import com.dk.backkp.security.UserPrincipal;
import com.dk.backkp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity getCategories() {
        try {
            return ResponseEntity.ok(categoryService.getAllCategory());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }


}

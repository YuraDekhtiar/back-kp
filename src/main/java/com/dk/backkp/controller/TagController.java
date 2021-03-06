package com.dk.backkp.controller;

import com.dk.backkp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    @Autowired
    TagService tagService;
    @GetMapping("/tag/{tag}")
    public ResponseEntity tag(@PathVariable String tag) {
        tagService.add(tag);

        return ResponseEntity.ok( "ok");
    }
}

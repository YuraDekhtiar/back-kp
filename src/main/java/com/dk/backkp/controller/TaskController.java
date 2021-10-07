package com.dk.backkp.controller;

import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.repository.ImgUrlRepository;
import com.dk.backkp.repository.TaskRepository;

import com.dk.backkp.security.CurrentUser;
import com.dk.backkp.security.UserPrincipal;
import com.dk.backkp.service.MyTaskService;
import com.dk.backkp.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    MyTaskService myTaskService;

    @Autowired
    UploadService uploadService;

    @PostMapping
    public ResponseEntity addNewTask(@RequestBody MyTaskEntity task,
                                     @CurrentUser UserPrincipal userPrincipal) throws IOException {
        myTaskService.addNewTask(task, userPrincipal);

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(myTaskService.getTaskById(id));

    }

}

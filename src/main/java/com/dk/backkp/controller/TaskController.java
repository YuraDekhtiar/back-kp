package com.dk.backkp.controller;

import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.model.MyTask;
import com.dk.backkp.repository.ImgUrlRepository;
import com.dk.backkp.repository.TaskRepository;

import com.dk.backkp.security.CurrentUser;
import com.dk.backkp.security.UserPrincipal;
import com.dk.backkp.service.MyTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ImgUrlRepository imgUrlRepository;

    @Autowired
    MyTaskService myTaskService;

    @PostMapping
    public ResponseEntity addNewTask(@RequestBody MyTaskEntity task, @CurrentUser UserPrincipal userPrincipal) {
        myTaskService.addNewTask(task, userPrincipal);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{id}")
    public ResponseEntity getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(myTaskService.getTaskById(id));

    }

}

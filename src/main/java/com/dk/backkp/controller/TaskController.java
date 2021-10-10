package com.dk.backkp.controller;

import com.dk.backkp.dto.MyTask;
import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.entity.RatingEntity;
import com.dk.backkp.exception.BadRequestException;
import com.dk.backkp.security.CurrentUser;
import com.dk.backkp.security.UserPrincipal;
import com.dk.backkp.service.MyTaskService;
import com.dk.backkp.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    MyTaskService myTaskService;


    @PostMapping
    public ResponseEntity addNewTask(@RequestBody MyTaskEntity task,
                                     @CurrentUser UserPrincipal userPrincipal) {
        myTaskService.addNewTask(task, userPrincipal);

        return ResponseEntity.ok("ok");
    }

    @GetMapping("/{id}")
    public ResponseEntity getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(MyTask.toModel(myTaskService.getTaskById(id)));
    }

    @GetMapping("/{id}/answer/{value}")
    public ResponseEntity getAnswer(@PathVariable Long id, @PathVariable String value,
                                      @CurrentUser UserPrincipal userPrincipal) {
        try {
            return ResponseEntity.ok(myTaskService.compareAnswer(id, value, userPrincipal.getId()));
        }
        catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/{id}/answered")
    public ResponseEntity userAnswered(@PathVariable Long id,

                                    @CurrentUser UserPrincipal userPrincipal) {
        try {
            return ResponseEntity.ok(myTaskService.userAnswered(id, userPrincipal.getId()));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }

    }

}

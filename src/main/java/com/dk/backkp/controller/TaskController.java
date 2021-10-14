package com.dk.backkp.controller;

import com.dk.backkp.dto.MyTask;
import com.dk.backkp.dto.MyTaskEdit;
import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.exception.BadRequestException;
import com.dk.backkp.security.CurrentUser;
import com.dk.backkp.security.UserPrincipal;
import com.dk.backkp.service.MyTaskService;
import com.dk.backkp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    MyTaskService myTaskService;

    @Autowired
    TagService tagService;

    @PostMapping
    public ResponseEntity addNewTask(@RequestBody MyTaskEntity task,
                                     @CurrentUser UserPrincipal userPrincipal) {
         //return ResponseEntity.ok(1);

        try {
            return ResponseEntity.ok(myTaskService.addNewTask(task, userPrincipal).getId());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getTaskById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(MyTask.toModel(myTaskService.getTaskById(id)));
        }
        catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity getTaskByIdForEdit(@PathVariable Long id, @CurrentUser UserPrincipal userPrincipal) {
        try {
            return ResponseEntity.ok(MyTaskEdit.toModel(myTaskService.getTaskByIdForEdit(id, userPrincipal.getId())));
        }
        catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity deleteById(@RequestBody List<Long> id,
                                     @CurrentUser UserPrincipal userPrincipal) throws Exception {
    try {
            return ResponseEntity.ok(myTaskService.deleteByListId(id, userPrincipal.getId()));
        }
        catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id, @CurrentUser UserPrincipal userPrincipal) {
    try {
            return ResponseEntity.ok(myTaskService.deleteById(id, userPrincipal.getId()));
        }
        catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }


    @GetMapping(params = {"limit", "page", "sort"})
    public ResponseEntity getTasks(@RequestParam int limit, @RequestParam int page, @RequestParam String sort) {
        try {
            return ResponseEntity.ok(MyTask.toModel(myTaskService.getTasksPage(page, limit, sort)));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping(value = "/{id}", params = {"answer"})
    public ResponseEntity getAnswer(@PathVariable Long id, @RequestParam String answer,
                                    @CurrentUser UserPrincipal userPrincipal) {
        try {
            return ResponseEntity.ok(myTaskService.compareAnswer(id, answer, userPrincipal.getId()));
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
            return ResponseEntity.ok(myTaskService.taskCompleted(id, userPrincipal.getId()));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping(params = {"user_id"})
    public ResponseEntity getAllTaskByUserId(@RequestParam Long user_id) {
        try {
            return ResponseEntity.ok(MyTask.toModel(myTaskService.getAllByUserId(user_id)));
        }
        catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }








/*/////////////////////////////////////////////*/
    @GetMapping("/tag/{tag}")
    public ResponseEntity tag(@PathVariable String tag) {
        tagService.add(tag);

        return ResponseEntity.ok( "ok");
    }


}

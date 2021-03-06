package com.dk.backkp.controller;

import com.dk.backkp.dto.MyTask;
import com.dk.backkp.dto.MyTaskEdit;
import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.exception.BadRequestException;
import com.dk.backkp.security.CurrentUser;
import com.dk.backkp.security.UserPrincipal;
import com.dk.backkp.service.MyTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    MyTaskService myTaskService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity addNewTask(@RequestBody MyTaskEntity task,
                                     @CurrentUser UserPrincipal userPrincipal) {
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

    @GetMapping(params = {"search"})
    public ResponseEntity search(@RequestParam String search) {
        try {
            return ResponseEntity.ok(MyTask.toModel(myTaskService.searchTask(search)));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
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
    @PreAuthorize("hasRole('ROLE_USER')")
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

    @GetMapping(params = {"limit", "page", "sort"})
    public ResponseEntity getTasks(@RequestParam int limit, @RequestParam int page, @RequestParam String sort) {
        try {
            Page<MyTaskEntity> tasksPage = myTaskService.getTasksPage(page, limit, sort);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Expose-Headers", "x-total-count");
            headers.add("x-total-count", tasksPage.getTotalElements() + "");

            return ResponseEntity.ok().headers(headers).body(MyTask.toModel(tasksPage));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping(value = "/{id}", params = {"answer"})
    @PreAuthorize("hasRole('ROLE_USER')")
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
    @PreAuthorize("hasRole('ROLE_USER')")
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
    @PreAuthorize("hasRole('ROLE_USER')")
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
}

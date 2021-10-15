package com.dk.backkp.controller;

import com.dk.backkp.security.CurrentUser;
import com.dk.backkp.security.UserPrincipal;
import com.dk.backkp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        try {
            return ResponseEntity.ok(userService.getUserById(userPrincipal.getId()));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}

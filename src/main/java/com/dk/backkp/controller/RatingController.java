package com.dk.backkp.controller;

import com.dk.backkp.exception.BadRequestException;
import com.dk.backkp.security.CurrentUser;
import com.dk.backkp.security.UserPrincipal;
import com.dk.backkp.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping("/{id}/{value}")
    public ResponseEntity setRating(@PathVariable Long id, @PathVariable Byte value,
                                    @CurrentUser UserPrincipal userPrincipal) {
        try {
            return ResponseEntity.ok(ratingService.setRating(id, value, userPrincipal));
        }
        catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}

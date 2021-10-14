package com.dk.backkp.controller;

import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.security.CurrentUser;
import com.dk.backkp.security.UserPrincipal;
import com.dk.backkp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping
public class UploadController {
    @Autowired
    FileService fileService;


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam(value="file") MultipartFile[] files) throws Exception {
        return ResponseEntity.ok(fileService.upload(files));
    }
}

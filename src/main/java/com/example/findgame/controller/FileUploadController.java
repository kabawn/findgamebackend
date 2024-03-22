package com.example.findgame.controller;

import com.example.findgame.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private StorageService storageService;
    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String filename = storageService.store(file);
        // Use ServletUriComponentsBuilder to build the full URL
        String fileAccessUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploaded-images/")
                .path(filename)
                .toUriString(); // Constructs the full URL to access the file

        return ResponseEntity.ok().body(fileAccessUrl);
    }
}


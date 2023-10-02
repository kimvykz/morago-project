package com.habsida.moragoproject.controller.rest;

import com.habsida.moragoproject.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class FileRestController {
    private FileService fileService;

    public FileRestController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRANSLATOR')")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(fileService.uploadFileByRestController(multipartFile));
    }

    @GetMapping("/{fileName}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRANSLATOR')")
    public ResponseEntity<?> downloadImage (@PathVariable String fileName) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpg"))
                .body(fileService.downloadFileByRestController(fileName));
    }

}

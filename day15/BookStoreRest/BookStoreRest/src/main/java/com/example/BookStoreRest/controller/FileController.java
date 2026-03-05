package com.example.BookStoreRest.controller;

import com.example.BookStoreRest.dto.FileDto;
import com.example.BookStoreRest.entity.FileEntity;
import com.example.BookStoreRest.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    // Upload endpoint
    @PostMapping("/upload")
    public ResponseEntity<FileDto> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        FileDto savedFile = fileService.uploadFile(file);
        return ResponseEntity.ok(savedFile);
    }

    // Download endpoint
    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {

        FileEntity fileEntity = fileService.downloadFile(id);

        ByteArrayResource resource =
                new ByteArrayResource(fileEntity.getFileData());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                .contentLength(fileEntity.getFileSize())
                .body(resource);
    }
}
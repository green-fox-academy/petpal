package com.greenfoxacademy.petpal.filestorage;

import com.greenfoxacademy.petpal.exception.FileStorageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileStorageController {
  private FileStorageService fileStorageService;

  public FileStorageController(FileStorageService fileStorageService) {
    this.fileStorageService = fileStorageService;
  }

  @PostMapping("/uploadFile")
  public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws FileStorageException {
    String fileName = fileStorageService.storeFile(file);
    return ResponseEntity.ok().body(String.format("%s successfully uploaded ", fileName));
  }
}

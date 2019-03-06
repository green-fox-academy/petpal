package com.greenfoxacademy.petpal.filestorage;

import com.greenfoxacademy.petpal.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

  private final Path fileStorageLocation;

  @Autowired
  public FileStorageService(FileStorageProperties fileStorageProperties) throws IOException, FileStorageException {
    this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
            .toAbsolutePath().normalize();

    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (Exception ex) {
      throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
    }
  }

  public String storeFile(MultipartFile file) throws FileStorageException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    System.out.println(fileName);

    try {
      if (fileName.contains("..")) {
        throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
      }

      Path targetLocation = this.fileStorageLocation.resolve(fileName);
      System.out.println(file.getInputStream());
      System.out.println(targetLocation);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      return fileName;
    } catch (IOException ex) {
      throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
    }
  }
}
package com.greenfoxacademy.petpal.imagestorage;

import com.greenfoxacademy.petpal.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ImageStorageService {

  private final Path fileStorageLocation;

  @Autowired
  public ImageStorageService(ImageStorageProperties imageStorageProperties) throws FileStorageException {
    this.fileStorageLocation = Paths.get(imageStorageProperties.getUploadDir())
            .toAbsolutePath().normalize();

    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (Exception ex) {
      throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
    }
  }

  public String storeFile(MultipartFile file) throws FileStorageException {
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

    try {
      if (fileName.contains("..")) {
        throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
      }

      if (ImageIO.read(file.getInputStream()) == null) {
        throw new FileStorageException("Filme must be an image: " + fileName + ". Only BMP, GIF, JPG and PNG are accepted.");
      }

      Path targetLocation = this.fileStorageLocation.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      return fileName;
    } catch (IOException ex) {
      throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
    }
  }
}

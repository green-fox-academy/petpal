package com.greenfoxacademy.petpal;

import com.greenfoxacademy.petpal.imagestorage.ImageStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
@EnableConfigurationProperties({
        ImageStorageProperties.class
}
)
public class PetpalApplication {

  public static void main(String[] args) {
    SpringApplication.run(PetpalApplication.class, args);
  }

}
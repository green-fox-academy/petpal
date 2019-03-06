package com.greenfoxacademy.petpal;

import com.greenfoxacademy.petpal.filestorage.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@SpringBootApplication
@RestController
//@EnableOAuth2Client
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class PetpalApplication {

  @RequestMapping("/user")
  public Principal user(Principal principal) {
    return principal;
  }

  public static void main(String[] args) {
    SpringApplication.run(PetpalApplication.class, args);
  }

}

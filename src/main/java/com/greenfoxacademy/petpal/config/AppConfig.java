/*package com.greenfoxacademy.petpal.config;

import com.greenfoxacademy.petpal.users.models.GoogleUser;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
public class AppConfig {

  @Bean
  public ParentUser getParentUser(ParentUser parentUser) throws Exception {
    if (parentUser instanceof PrivateUser) {
      return new PrivateUser();
    }
    if (parentUser instanceof GoogleUser) {
      return new GoogleUser();
    }
    throw new Exception("Bean instantiation not working properly");
  }
}*/
package com.greenfoxacademy.petpal.config;
import com.greenfoxacademy.petpal.users.services.GoogleUserServiceImpl;
import com.greenfoxacademy.petpal.users.services.ParentUserService;
import com.greenfoxacademy.petpal.users.services.PrivateUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
  @Bean
  @Primary
  public ParentUserService getParentUserService(ParentUserService userDetailsService) throws Exception {
    if (userDetailsService instanceof PrivateUserServiceImpl) {
      return new PrivateUserServiceImpl();
    }
    if (userDetailsService instanceof GoogleUserServiceImpl) {
      return new GoogleUserServiceImpl();
    }
    throw new Exception("Bean instantiation not working properly");
  }
}
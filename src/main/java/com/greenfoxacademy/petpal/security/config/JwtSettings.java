package com.greenfoxacademy.petpal.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class JwtSettings {

  public static final long ACCESS_TOKEN_LIFETIME =300;
  public static final long REFRESH_TOKEN_LIFETIME = 800;

  public static final String TOKEN_ISSUER = "PetPal";

  public static final String TOKEN_SIGNING_KEY = System.getenv("PETPAL_TOKEN_SIGNING_KEY");

}

package com.greenfoxacademy.petpal.oauthSecurity;

public class Constants {
  public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
  public static final String SIGNING_KEY = System.getenv("PETPAL_TOKEN_SIGNING_KEY");
  public static final String TOKEN_PARAM = "auth_token";
  public static final String homeUrl = System.getenv("HOME_URL");
}

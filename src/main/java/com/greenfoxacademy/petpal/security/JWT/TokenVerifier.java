package com.greenfoxacademy.petpal.security.JWT;

public interface TokenVerifier {
  boolean verify(String jti);
}

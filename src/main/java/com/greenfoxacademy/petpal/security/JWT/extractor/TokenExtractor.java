package com.greenfoxacademy.petpal.security.JWT.extractor;

public interface TokenExtractor {
  String extract(String payload);
}

package com.greenfoxacademy.petpal;

import com.greenfoxacademy.petpal.oauthSecurity.JwtTokenUtil;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import org.springframework.beans.factory.annotation.Autowired;

public class TestTokenProvider {

  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  public TestTokenProvider(JwtTokenUtil jwtTokenUtil) {
    this.jwtTokenUtil = jwtTokenUtil;
  }

  public String createMockToken(ParentUser parentUser) {
    String token = jwtTokenUtil.generateToken(parentUser);
    return "Bearer " + token;
  }
}

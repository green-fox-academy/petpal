package com.greenfoxacademy.petpal.oauthSecurity;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
public class UserContext {
  private final String email;
  private final List<GrantedAuthority> authorities;

  private UserContext(String email, List<GrantedAuthority> authorities) {
    this.email = email;
    this.authorities = authorities;
  }

  public static UserContext create(String email, List<GrantedAuthority> authorities) {
    return new UserContext(email, authorities);
  }

}

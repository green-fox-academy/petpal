package com.greenfoxacademy.petpal.oauthSecurity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import java.util.Set;

@Getter
public class UserContext {
  private final String username;
  private final Set<GrantedAuthority> authorities;

  private UserContext(String username, Set<GrantedAuthority> authorities) {
    this.username = username;
    this.authorities = authorities;
  }

  public static UserContext create(String username, Set<GrantedAuthority> authorities) {
    return new UserContext(username, authorities);
  }
}

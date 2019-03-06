package com.greenfoxacademy.petpal.security.ajax;

import com.greenfoxacademy.petpal.security.model.UserContext;
import com.greenfoxacademy.petpal.users.MainUserService;
import com.greenfoxacademy.petpal.users.PrivateUser;
import com.greenfoxacademy.petpal.users.PrivateUserServiceImpl;
import com.greenfoxacademy.petpal.users.SuperUser;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import static java.util.Collections.emptyList;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

  private final BCryptPasswordEncoder encoder;
  private final MainUserService userService;

  @Autowired
  public AjaxAuthenticationProvider(final BCryptPasswordEncoder encoder, final MainUserService userService) {
    this.encoder = encoder;
    this.userService = userService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException, UsernameNotFoundException {
    Assert.notNull(authentication, "No authentication data provided");
    String username = (String) authentication.getPrincipal();
    String password = (String) authentication.getCredentials();
    SuperUser superUser = null;
    try {
      superUser = (SuperUser) userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No such user: " + username));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }

    if (!encoder.matches(password, superUser.getPassword())) {
      throw new BadCredentialsException("Wrong password!");
    }

    UserContext userContext = UserContext.create(superUser.getUsername(), emptyList());
    return new UsernamePasswordAuthenticationToken(userContext, null, emptyList());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }
}

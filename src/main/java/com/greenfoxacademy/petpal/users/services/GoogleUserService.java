package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.services.AnimalService;
import com.greenfoxacademy.petpal.exception.EmailTakenException;
import com.greenfoxacademy.petpal.oauthSecurity.JwtTokenUtil;
import com.greenfoxacademy.petpal.users.models.GoogleUser;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.greenfoxacademy.petpal.users.repositories.MainUserRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class GoogleUserServiceImpl extends ParentUserService<GoogleUser> {

  @Autowired
  private MainUserRepository userRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  public GoogleUserServiceImpl(MainUserRepository<ParentUser> mainUserRepository, AnimalService animalService) {
    super(mainUserRepository, animalService);
  }

  @Override
  public String login(GoogleUser googleUser) {
    userRepository.save(googleUser);
    return jwtTokenUtil.generateToken(googleUser);
  }

  @Override
  public GoogleUser register(GoogleUser googleUser) throws EmailTakenException, UnirestException {
    return null;
  }

  @Override
  public GoogleUser changeUserDetails(GoogleUser googleUser) {
    return null;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }
}

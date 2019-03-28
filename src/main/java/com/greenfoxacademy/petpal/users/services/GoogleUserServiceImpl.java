package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.exception.EmailTakenException;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

<<<<<<<HEAD

public class GoogleUserServiceImpl extends ParentUserService<GoogleUser> {

  @Autowired
  private MainUserRepository userRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

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
  public Set<Animal> animalsLikedByUser(GoogleUser googleUser) {
    return null;
  }

  @Override
  public Set<Animal> animalsToAdoptByUser(GoogleUser googleUser) {
    return null;
  }

  @Override
  public void addAnimalToAnimalsLikedByUser(Animal animal, GoogleUser googleUser) {

  }

  @Override
  public void addAnimalToAnimalsToAdoptByUser(Animal animal, GoogleUser googleUser) {

  }

  @Override
  public void addAnimalToAnimalsOwnedByUser(Animal animal, GoogleUser googleUser) {

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }
}

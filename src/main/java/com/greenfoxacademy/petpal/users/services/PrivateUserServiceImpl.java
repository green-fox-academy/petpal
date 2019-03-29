package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.services.AnimalService;
import com.greenfoxacademy.petpal.exception.EmailTakenException;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;
import com.greenfoxacademy.petpal.geocode.GeoCodeService;
import com.greenfoxacademy.petpal.oauthSecurity.JwtTokenUtil;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import com.greenfoxacademy.petpal.users.repositories.MainUserRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PrivateUserServiceImpl extends ParentUserService<PrivateUser> {

  @Autowired
  private BCryptPasswordEncoder encoder;
  @Autowired
  private GeoCodeService locationService;
  @Autowired
  private MainUserRepository mainUserRepository;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  public PrivateUserServiceImpl(MainUserRepository<ParentUser> mainUserRepository, AnimalService animalService) {
    super(mainUserRepository, animalService);
  }

  @Override
  public String login(PrivateUser privateUser) throws UserNotFoundException {
    if (!isEmailInDB(privateUser)) {
      throw new UserNotFoundException();
    }
    return jwtTokenUtil.generateToken(privateUser);
  }

  @Override
  public PrivateUser register(PrivateUser privateUser) throws EmailTakenException, UnirestException {
    if (!isEmailInDB(privateUser)) {
      privateUser.setPassword(encoder.encode(privateUser.getPassword()));
      //   GeoCode geoCode = locationService.generateUserLocationFromAddress(privateUser);
      //  privateUser.setGeoCode(geoCode);
      return (PrivateUser) saveUser(privateUser);
    }
    throw new EmailTakenException("Email address already taken, please choose an other one or sign in.");
  }

  @Override
  public PrivateUser changeUserDetails(PrivateUser privateUser) {
    return null;
  }

  private List<SimpleGrantedAuthority> getAuthority() {
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    PrivateUser user = (PrivateUser) mainUserRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new User(user.getEmail(), user.getPassword(), getAuthority());
  }
}




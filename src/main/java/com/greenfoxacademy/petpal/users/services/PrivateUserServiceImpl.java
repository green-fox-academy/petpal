package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.exception.AnimalAlreadyAdoptedException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;
import com.greenfoxacademy.petpal.exception.UsernameTakenException;
import com.greenfoxacademy.petpal.geocode.GeoCode;
import com.greenfoxacademy.petpal.geocode.GeoCodeService;
import com.greenfoxacademy.petpal.oauthSecurity.GoogleOAuth2UserInfo;
import com.greenfoxacademy.petpal.oauthSecurity.JwtTokenUtil;
import com.greenfoxacademy.petpal.oauthSecurity.UserContext;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import com.greenfoxacademy.petpal.users.models.SuperUser;
import com.greenfoxacademy.petpal.users.repositories.MainUserRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.*;

@Service
public class PrivateUserServiceImpl extends OidcUserService implements PrivateUserService {

  private MainUserRepository mainUserRepository;
  private BCryptPasswordEncoder encoder;
  private GeoCodeService locationService;
  private JwtTokenUtil jwtTokenUtil;
  private BCryptPasswordEncoder bcryptEncoder;

  @Autowired
  public PrivateUserServiceImpl(MainUserRepository mainUserRepository, BCryptPasswordEncoder encoder, GeoCodeService locationService, JwtTokenUtil jwtTokenUtil, BCryptPasswordEncoder bcryptEncoder) {
    this.mainUserRepository = mainUserRepository;
    this.encoder = encoder;
    this.locationService = locationService;
    this.jwtTokenUtil = jwtTokenUtil;
    this.bcryptEncoder = bcryptEncoder;
  }

  @Override
  public PrivateUser registerNewUser(PrivateUser privateUser) throws UsernameTakenException, UserIsNullException, UnirestException {
    if (!mainUserRepository.existsByUsername(privateUser.getUsername())) {
      privateUser.setPassword(encoder.encode(privateUser.getPassword()));
      GeoCode geoCode = locationService.generateUserLocationFromAddress(privateUser);
      privateUser.setGeoCode(geoCode);
      return saveUser(privateUser);
    }
    throw new UsernameTakenException("Username already taken, please choose an other one.");

  }

  @Override
  public Optional<PrivateUser> findByUsername(String username) {
    return mainUserRepository.findByUsername(username);
  }

  @Override
  public PrivateUser findById(Long id) throws Throwable {
    return (PrivateUser) mainUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(("There is no User with such ID")));
  }

  @Override
  public PrivateUser saveUser(PrivateUser privateUser) throws UserIsNullException {
    checkIfUserIsnull(privateUser);
    return (PrivateUser) mainUserRepository.save(privateUser);
  }

  @Override
  public void removeUser(Long id) throws UserNotFoundException {
    if (!mainUserRepository.existsById(id)) {
      throw new UserNotFoundException("There is no User with such ID");
    }
    mainUserRepository.deleteById(id);
  }

  @Override
  public Set<Animal> animalsLikedByUser(Long userId) throws Throwable {
    return findById(userId).getAnimalsLikedByUser();
  }

  @Override
  public Set<Animal> animalsToAdoptByUser(Long userId) throws Throwable {
    //return findById(userId).getAnimalsToAdoptByUser();
    return null;
  }

  @Override
  public Set<Animal> animalsOwnedByUser(Long userId) throws Throwable {
    return findById(userId).getOwnedAnimalsByUser();
  }

  @Override
  public void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser) throws Throwable {
    if (animal.getAdopted()) {
      throw new AnimalAlreadyAdoptedException("This pet has been already adopted.");
    }
    Set<Animal> animalsLikedByUser = animalsLikedByUser(privateUser.getId());
    animalsLikedByUser.add(animal);
    privateUser.setAnimalsLikedByUser(animalsLikedByUser);
    System.out.println(animalsLikedByUser);
    Set<PrivateUser> privateUsers = animal.getPrivateUser();
    privateUsers.add(privateUser);
    animal.setPrivateUser(privateUsers);
    saveUser(privateUser);
  }

  @Override
  public void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser) throws Throwable {
    if (animal.getAdopted()) {
      throw new AnimalAlreadyAdoptedException("This pet has been already adopted.");
    }
    Set<Animal> animalsToAdoptByUser = animalsToAdoptByUser(privateUser.getId());
    animal.setAdopted(true);
    animalsToAdoptByUser.add(animal);
    //privateUser.setAnimalsToAdoptByUser(animalsToAdoptByUser);
    saveUser(privateUser);
  }

  @Override
  public void addAnimalToAnimalsOwnedByUser(Animal animal, PrivateUser privateUser) throws Throwable {
    Set<Animal> animalsOwnedByUser = animalsOwnedByUser(privateUser.getId());
    animalsOwnedByUser.add(animal);
    privateUser.setOwnedAnimalsByUser(animalsOwnedByUser);
    saveUser(privateUser);
  }

  @Override
  public void checkIfUserIsnull(PrivateUser privateUser) throws UserIsNullException {
    if (privateUser == null) {
      throw new UserIsNullException("User must not be null");
    }
  }

  @Override
  public Optional<PrivateUser> getUserFromAuth(Authentication authentication) {
    UserContext userContext = (UserContext) authentication.getPrincipal();
    return findByUsername(userContext.getUsername());
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


    PrivateUser user = (PrivateUser) mainUserRepository.findByEmail(email);
    if(user == null){
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new User(user.getEmail(), user.getPassword(), getAuthority());
  }

  private List<SimpleGrantedAuthority> getAuthority() {
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String signUp(SuperUser user) {
    PrivateUser dbUser = (PrivateUser) mainUserRepository.findByEmail(user.getEmail());
    if (dbUser != null) {
      throw new RuntimeException("User already exist.");
    }
    user.setPassword(bcryptEncoder.encode(user.getPassword()));
    mainUserRepository.save(user);
    return jwtTokenUtil.generateToken(user);
  }

  @Override
  public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
    OidcUser oidcUser = super.loadUser(userRequest);
    Map<String, Object> attributes = oidcUser.getAttributes();
    GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo();
    userInfo.setEmail((String) attributes.get("email"));
    userInfo.setId((String) attributes.get("sub"));
    userInfo.setImageUrl((String) attributes.get("picture"));
    userInfo.setName((String) attributes.get("name"));
    updateUser(userInfo);

    return oidcUser;
  }


  private void updateUser(GoogleOAuth2UserInfo userInfo) {
    PrivateUser user = (PrivateUser) mainUserRepository.findByEmail(userInfo.getEmail());
    if(user == null){
      user = new PrivateUser();
    }
    user.setEmail(userInfo.getEmail());
    user.setImageUrl(userInfo.getImageUrl());
    user.setUsername(userInfo.getName());
    mainUserRepository.save(user);
  }
}

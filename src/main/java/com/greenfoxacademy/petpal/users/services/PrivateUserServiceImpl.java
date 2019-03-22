package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.exception.EmailTakenException;
import com.greenfoxacademy.petpal.geocode.GeoCode;
import com.greenfoxacademy.petpal.geocode.GeoCodeService;
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

import java.util.*;

@Service
public class PrivateUserServiceImpl extends ParentUserService<PrivateUser> {

  @Autowired
  private BCryptPasswordEncoder encoder;
  @Autowired
  private GeoCodeService locationService;
  @Autowired
  private MainUserRepository mainUserRepository;

  @Override
  public PrivateUser login(PrivateUser privateUser) {
    return null;
  }

  @Override
  public PrivateUser register(PrivateUser privateUser) throws EmailTakenException, UnirestException {

    if (!isUserInDB(privateUser)) {
      privateUser.setPassword(encoder.encode(privateUser.getPassword()));
      GeoCode geoCode = locationService.generateUserLocationFromAddress(privateUser);
      privateUser.setGeoCode(geoCode);
      return saveUser(privateUser);
    }
    throw new EmailTakenException("Email address already taken, please choose an other one or sign in.");

  }

  @Override
  public Set<Animal> animalsLikedByUser(PrivateUser privateUser) {
    return privateUser.getAnimalsLikedByUser();
  }

  @Override
  public Set<Animal> animalsToAdoptByUser(PrivateUser privateUser) {
    return privateUser.getAnimalsToAdoptByUser();
  }

  @Override
  public void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser) {
    //TODO: implement the method, scroll down
  }

  @Override
  public void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser) {
    //TODO: implement the method, scroll down

  }

  @Override
  public void addAnimalToAnimalsOwnedByUser(Animal animal, PrivateUser privateUser) {
    //TODO: implement the method, scroll down

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

/*
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
  */


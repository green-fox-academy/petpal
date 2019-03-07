package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import com.greenfoxacademy.petpal.exception.UsernameTakenException;
import com.greenfoxacademy.petpal.geocode.GeoCode;
import com.greenfoxacademy.petpal.geocode.GeoCodeService;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import com.greenfoxacademy.petpal.users.repositories.MainUserRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PrivateUserServiceImpl implements PrivateUserService {

  private MainUserRepository mainUserRepository;
  private BCryptPasswordEncoder encoder;
  private GeoCodeService locationService;

  @Autowired
  public PrivateUserServiceImpl(MainUserRepository mainUserRepository, BCryptPasswordEncoder encoder, GeoCodeService locationService) {
    this.mainUserRepository = mainUserRepository;
    this.encoder = encoder;
    this.locationService = locationService;
  }

  @Override
  public PrivateUser registerNewUser(PrivateUser privateUser) throws UsernameTakenException, UserIsNullException, UnirestException {
    if (!mainUserRepository.existsByUsername(privateUser.getUsername())) {
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
    privateUser.setPassword(encoder.encode(privateUser.getPassword()));
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
    return findById(userId).getAnimalsToAdoptByUser();
  }

  @Override
  public Set<Animal> animalsOwnedByUser(Long userId) throws Throwable {
    return findById(userId).getOwnedAnimalsByUser();
  }

  @Override
  public void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser) throws Throwable {
    Set<Animal> animalsLikedByUser = animalsLikedByUser(privateUser.getId());
    animalsLikedByUser.add(animal);
    privateUser.setAnimalsLikedByUser(animalsLikedByUser);
    saveUser(privateUser);
  }

  @Override
  public void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser) throws Throwable {
    Set<Animal> animalsToAdoptByUser = animalsToAdoptByUser(privateUser.getId());
    animalsToAdoptByUser.add(animal);
    privateUser.setAnimalsToAdoptByUser(animalsToAdoptByUser);
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

}

package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.services.AnimalService;
import com.greenfoxacademy.petpal.exception.EmailTakenException;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;
import com.greenfoxacademy.petpal.geocode.GeoCodeService;
import com.greenfoxacademy.petpal.oauthSecurity.UserContext;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.greenfoxacademy.petpal.users.repositories.MainUserRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service(value = "userDetailsService")
public abstract class ParentUserService<T extends ParentUser> implements UserDetailsService {

  @Autowired
  private MainUserRepository mainUserRepository;
  @Autowired
  private AnimalService animalService;
  @Autowired
  private GeoCodeService locationService;

  public T findByEmail(String email) throws Throwable {
    //TODO: set default message in the constructor of the exception class
    if(mainUserRepository.findByEmail(email) == null){
      throw new UsernameNotFoundException("There is no User with such email");
    }
    return (T) mainUserRepository.findByEmail(email);
  }

  public T findById(Long id) throws Throwable {
    //TODO: set default message in the constructor of the exception class
    return (T) mainUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(("There is no User with such ID")));
  }



  public T saveUser(T t) {
    return (T) mainUserRepository.save(t);
  }

  public void removeUser(T t) {
    mainUserRepository.delete(t);
  }

  public boolean isUserNull(T t) {
    return t == null;
  }

/*  public boolean isUserInDB(T t){
    return mainUserRepository.existsById(t.getId());
  }*/

  public boolean isEmailInDB(T t){
    return mainUserRepository.existsByEmail(t.getEmail());
  }


  public T getUserFromAuth(Authentication authentication) throws Throwable {
    return findByEmail((authentication.getName()));
  }

  public Set<Animal> animalsOwnedByUser(T t){
    return t.getOwnedAnimalsByUser();
  }

  public abstract String login(T t) throws UserNotFoundException;

  public abstract T register(T t) throws EmailTakenException, UnirestException;

  public abstract Set<Animal> animalsLikedByUser(T t);

  public abstract Set<Animal> animalsToAdoptByUser(T t);

  public abstract void addAnimalToAnimalsLikedByUser(Animal animal, T t);

  public abstract void addAnimalToAnimalsToAdoptByUser(Animal animal, T t);

  public abstract void addAnimalToAnimalsOwnedByUser(Animal animal, T t);
}

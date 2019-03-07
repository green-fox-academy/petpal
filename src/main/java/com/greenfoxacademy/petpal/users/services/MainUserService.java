package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import com.greenfoxacademy.petpal.exception.UsernameTakenException;
import com.greenfoxacademy.petpal.users.models.SuperUser;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.greenfoxacademy.petpal.animal.models.AnimalDTO;
import org.springframework.security.core.Authentication;

import java.util.Optional;
import java.util.Set;

public interface MainUserService<T extends SuperUser> {

  Optional<T> findByUsername(String username);

  T findById(Long id) throws Throwable;

  T saveUser(T t) throws UserIsNullException;

  void removeUser(Long id) throws UserNotFoundException;

  void checkIfUserIsnull(T t) throws UserIsNullException;

  Set<Animal> animalsOwnedByUser(Long userId) throws Throwable;

  void addAnimalToAnimalsOwnedByUser(AnimalDTO animalDTO, T t) throws Throwable;

  T registerNewUser(T t) throws UsernameTakenException, UserIsNullException, UnirestException;

  Optional<T> getUserFromAuth(Authentication authenticationn);
}

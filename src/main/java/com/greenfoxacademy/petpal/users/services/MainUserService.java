package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.models.AnimalDTO;
import com.greenfoxacademy.petpal.exception.UserIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import com.greenfoxacademy.petpal.exception.UsernameTakenException;
import com.greenfoxacademy.petpal.users.models.SuperUser;
import org.springframework.security.core.Authentication;

import java.util.Optional;
import java.util.Set;

public interface MainUserService<T extends SuperUser> {

  Optional<T> findByUsername(String username);

  T findById(Long id) throws Throwable;

  T saveUser(T t) throws UserIsNullException;

  void removeUser(Long id) throws UserIdNotFoundException;

  void checkIfUserIsnull(T t) throws UserIsNullException;

  Set<Animal> animalsOwnedByUser(Long userId) throws Throwable;

  void addAnimalToAnimalsOwnedByUser(AnimalDTO animalDTO, T t) throws Throwable;

  T registerNewUser(T t) throws UsernameTakenException, UserIsNullException;

  Optional<T> getUserFromAuth(Authentication authenticationn);
}

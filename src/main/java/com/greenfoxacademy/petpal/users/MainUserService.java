package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import com.greenfoxacademy.petpal.exception.UserIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;

import java.util.Optional;
import java.util.Set;

public interface MainUserService <T extends SuperUser>{

  Optional<T> findByUsername(String username);
  T findById(Long id) throws UserIdNotFoundException;

  T saveUser(T t) throws UserIsNullException;

  void removeUser(Long id) throws UserIdNotFoundException;
  void checkIfUserIsnull(T t) throws UserIsNullException;

  Set<Animal> ownedAnimalsByUser(Long userId) throws UserIdNotFoundException;

}

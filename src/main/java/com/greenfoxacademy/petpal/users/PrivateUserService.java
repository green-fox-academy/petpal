package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import com.greenfoxacademy.petpal.exception.UserIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import java.util.Set;

public interface PrivateUserService extends MainUserService<PrivateUser>{

  Set<Animal> animalsLikedByUser(Long userId) throws Throwable; //all list set
  Set<Animal> animalsToAdoptByUser(Long userId) throws Throwable;

  void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser) throws Throwable;
  void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser); //ugzanay mint adopt
  void addAnimalToOwnedAnimalsByUser(Animal animal, PrivateUser privateUser);
}

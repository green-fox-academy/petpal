package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;

import java.util.List;

public interface PrivateUserService {

  PrivateUser findById(Long id) throws UserNotFoundException;

  PrivateUser saveUser(PrivateUser privateUser);

  void removeUser(PrivateUser privateUser);

  List<Animal> likedAnimalsByUser(Long userId);

  List<Animal> favouriteAnimalsByUser(Long userId);

  void addAnimalToLikedAnimals(Animal animal, PrivateUser privateUser);

  void addAnimalToFavouriteAnimals(Animal animal, PrivateUser privateUser);

  void adoptAnimal(Animal animal, PrivateUser privateUser);

//  void markMyAnimalForAdoption(Animal animal);

}

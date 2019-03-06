package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;

import java.util.List;
import java.util.Optional;

public interface PrivateUserService {

  void findById(Long id);
  Optional<PrivateUser> findByUsername(String username);
  void saveUser(PrivateUser privateUser);
  void removeUser(PrivateUser privateUser);

  List<Animal> likedAnimalsByUser(Long userId);
  List<Animal> favouriteAnimalsByUser(Long userId);

  void addAnimalToLikedAnimals(Animal animal);
  void addAnimalToFavouriteAnimals(Animal animal);
  void adoptAnimal(Animal animal);

  void markMyAnimalForAdoption(Animal animal);


}

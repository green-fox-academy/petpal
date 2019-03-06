package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PrivateUserService {

  void findById(Long id);
  void saveUser(PrivateUser privateUser);
  void removeUser(PrivateUser privateUser);

  List<Animal> likedAnimalsByUser(Long userId);
  List<Animal> favouriteAnimalsByUser(Long userId);

  void addAnimalToLikedAnimals(Animal animal);
  void addAnimalToFavouriteAnimals(Animal animal);
  void adoptAnimal(Animal animal);

  void markMyAnimalForAdoption(Animal animal);


}

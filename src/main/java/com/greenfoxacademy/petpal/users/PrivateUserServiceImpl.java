package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateUserServiceImpl implements PrivateUserService {

  private PrivateUserRepository privateUserRepository;

  @Autowired
  public PrivateUserServiceImpl(PrivateUserRepository privateUserRepository) {
    this.privateUserRepository = privateUserRepository;
  }

  @Override
  public PrivateUser findById(Long id) throws UserNotFoundException {
    return privateUserRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Username not found"));
  }

  @Override
  public PrivateUser saveUser(PrivateUser privateUser) {

  }

  @Override
  public void removeUser(PrivateUser privateUser) {

  }

  @Override
  public List<Animal> likedAnimalsByUser(Long userId) {
    return null;
  }

  @Override
  public List<Animal> favouriteAnimalsByUser(Long userId) {
    return null;
  }

  @Override
  public void addAnimalToLikedAnimals(Animal animal, PrivateUser privateUser) {

  }

  @Override
  public void addAnimalToFavouriteAnimals(Animal animal, PrivateUser privateUser) {

  }

  @Override
  public void adoptAnimal(Animal animal, PrivateUser privateUser) {

  }

//  @Override
//  public void markMyAnimalForAdoption(Animal animal) {
//
//  }
}

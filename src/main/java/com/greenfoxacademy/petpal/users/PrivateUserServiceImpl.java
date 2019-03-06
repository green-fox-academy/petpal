package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivateUserServiceImpl implements PrivateUserService {

  private PrivateUserRepository privateUserRepository;

  @Autowired
  public PrivateUserServiceImpl(PrivateUserRepository privateUserRepository) {
    this.privateUserRepository = privateUserRepository;
  }

  @Override
  public void findById(Long id) {

  }

  @Override
  public Optional<PrivateUser> findByUsername(String username) {
    return privateUserRepository.findByUsername(username);
  }

  @Override
  public void saveUser(PrivateUser privateUser) {

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
  public void addAnimalToLikedAnimals(Animal animal) {

  }

  @Override
  public void addAnimalToFavouriteAnimals(Animal animal) {

  }

  @Override
  public void adoptAnimal(Animal animal) {

  }

  @Override
  public void markMyAnimalForAdoption(Animal animal) {

  }
}

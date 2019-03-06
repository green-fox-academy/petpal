package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import com.greenfoxacademy.petpal.animal.AnimalRepository;
import com.greenfoxacademy.petpal.exception.UserIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PrivateUserServiceImpl implements PrivateUserService {

  private PrivateUserRepository privateUserRepository;
  private AnimalRepository animalRepository;

  @Autowired
  public PrivateUserServiceImpl(PrivateUserRepository privateUserRepository, AnimalRepository animalRepository) {
    this.privateUserRepository = privateUserRepository;
    this.animalRepository = animalRepository;
  }

  @Override
  public Optional<PrivateUser> findByUsername(String username) {
    return Optional.empty();
  }

  @Override
  public void removeUser(PrivateUser privateUser) {

  }

  @Override
  public PrivateUser findById(Long id) throws UserIdNotFoundException {
    return null;
  }

  @Override
  public PrivateUser saveUser(PrivateUser privateUser) throws UserIsNullException {
    return null;
  }

  @Override
  public void removeUser(Long id) throws UserIdNotFoundException {

  }

  @Override
  public Set<Animal> animalsLikedByUser(Long userId) throws UserIdNotFoundException {
    return null;
  }

  @Override
  public Set<Animal> animalsToAdoptByUser(Long userId) throws UserIdNotFoundException {
    return null;
  }

  @Override
  public List<Animal> ownedAnimalsByUser(Long userId) throws UserIdNotFoundException {
    return null;
  }

  @Override
  public void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser) {

  }

  @Override
  public void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser) {

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

  @Override
  public void addAnimalToOwnedAnimalsByUser(Animal animal, PrivateUser privateUser) {

  }

  @Override
  public void checkIfUserIsnull(PrivateUser privateUser) throws UserIsNullException {

  }
}


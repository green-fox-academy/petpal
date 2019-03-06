package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import com.greenfoxacademy.petpal.animal.AnimalRepository;
import com.greenfoxacademy.petpal.exception.UserIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    return privateUserRepository.findByUsername(username);
  }

  @Override
  public PrivateUser findById(Long id) throws UserIdNotFoundException {
    return privateUserRepository.findById(id)
            .orElseThrow(() -> new UserIdNotFoundException(("There is no User with such ID")));
  }


  @Override
  public PrivateUser saveUser(PrivateUser privateUser) throws UserIsNullException {
    checkIfUserIsnull(privateUser);
    return privateUserRepository.save(privateUser);
  }

  @Override
  public void removeUser(Long id) throws UserIdNotFoundException {
    if (!privateUserRepository.existsById(id)) {
      throw new UserIdNotFoundException("There is no User with such ID");
    }
    privateUserRepository.deleteById(id);

  }

  @Override
  public Set<Animal> animalsLikedByUser(Long userId) throws UserIdNotFoundException {
    return findById(userId).getAnimalsLikedByUser();
  }

  @Override
  public Set<Animal> animalsToAdoptByUser(Long userId) throws UserIdNotFoundException {
    return findById(userId).getAnimalsToAdoptByUser();
  }

  @Override
  public Set<Animal> ownedAnimalsByUser(Long userId) throws UserIdNotFoundException {
    return findById(userId).getOwnedAnimalsByUser();
  }

  @Override
  public void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser) throws UserIdNotFoundException, UserIsNullException {
    Set<Animal> animalsLikedByUser = animalsLikedByUser(privateUser.getId());
    animalsLikedByUser.add(animal);
    privateUser.setAnimalsLikedByUser(animalsLikedByUser);
    saveUser(privateUser);
  }

  @Override
  public void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser) {

  }

  @Override
  public void addAnimalToOwnedAnimalsByUser(Animal animal, PrivateUser privateUser) {

  }

  @Override
  public void checkIfUserIsnull(PrivateUser privateUser) throws UserIsNullException {
    if (privateUser == null) {
      throw new UserIsNullException("User must not be null");
    }
  }

}

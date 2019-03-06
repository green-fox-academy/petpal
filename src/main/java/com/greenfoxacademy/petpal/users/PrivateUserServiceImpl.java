package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import com.greenfoxacademy.petpal.exception.UserIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PrivateUserServiceImpl implements PrivateUserService {

  private MainUserRepository mainUserRepository;
  private BCryptPasswordEncoder encoder;

  @Autowired
  public PrivateUserServiceImpl(MainUserRepository mainUserRepository, BCryptPasswordEncoder encoder) {
    this.mainUserRepository = mainUserRepository;
    this.encoder = encoder;
  }

  @Override
  public Optional<PrivateUser> findByUsername(String username) {
    return mainUserRepository.findByUsername(username);
  }

  @Override
  public PrivateUser findById(Long id) throws Throwable {
    return (PrivateUser) mainUserRepository.findById(id)
            .orElseThrow(() -> new UserIdNotFoundException(("There is no User with such ID")));
  }

  @Override
  public PrivateUser saveUser(PrivateUser privateUser) throws UserIsNullException {
    privateUser.setPassword(encoder.encode(privateUser.getPassword()));
    checkIfUserIsnull(privateUser);
    return (PrivateUser) mainUserRepository.save(privateUser);
  }

  @Override
  public void removeUser(Long id) throws UserIdNotFoundException {
    if (!mainUserRepository.existsById(id)) {
      throw new UserIdNotFoundException("There is no User with such ID");
    }
    mainUserRepository.deleteById(id);
  }

  @Override
  public Set<Animal> animalsLikedByUser(Long userId) throws Throwable {
    return findById(userId).getAnimalsLikedByUser();
  }

  @Override
  public Set<Animal> animalsToAdoptByUser(Long userId) throws Throwable {
    return findById(userId).getAnimalsToAdoptByUser();
  }

  @Override
  public Set<Animal> animalsOwnedByUser(Long userId) throws Throwable {
    return findById(userId).getOwnedAnimalsByUser();
  }

  @Override
  public void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser) throws Throwable {
    Set<Animal> animalsLikedByUser = animalsLikedByUser(privateUser.getId());
    animalsLikedByUser.add(animal);
    privateUser.setAnimalsLikedByUser(animalsLikedByUser);
    saveUser(privateUser);
  }

  @Override
  public void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser) throws Throwable {
    Set<Animal> animalsToAdoptByUser = animalsToAdoptByUser(privateUser.getId());
    animalsToAdoptByUser.add(animal);
    privateUser.setAnimalsToAdoptByUser(animalsToAdoptByUser);
    saveUser(privateUser);
  }

  @Override
  public void addAnimalToAnimalsOwnedByUser(Animal animal, PrivateUser privateUser) throws Throwable {
    Set<Animal> animalsOwnedByUser = animalsOwnedByUser(privateUser.getId());
    animalsOwnedByUser.add(animal);
    privateUser.setOwnedAnimalsByUser(animalsOwnedByUser);
    saveUser(privateUser);
  }

  @Override
  public void checkIfUserIsnull(PrivateUser privateUser) throws UserIsNullException {
    if (privateUser == null) {
      throw new UserIsNullException("User must not be null");
    }
  }

}

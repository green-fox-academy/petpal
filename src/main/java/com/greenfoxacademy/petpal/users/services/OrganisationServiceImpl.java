package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.AnimalFactory;
import com.greenfoxacademy.petpal.animal.AnimalType;
import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;
import com.greenfoxacademy.petpal.exception.UsernameTakenException;
import com.greenfoxacademy.petpal.users.repositories.MainUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.Set;

public class OrganisationServiceImpl implements OrganisationService {

  private MainUserRepository mainUserRepository;
  private BCryptPasswordEncoder encoder;

  @Autowired
  public OrganisationServiceImpl(MainUserRepository mainUserRepository, BCryptPasswordEncoder encoder) {
    this.mainUserRepository = mainUserRepository;
    this.encoder = encoder;
  }

  @Override
  public Optional<Organisation> findByUsername(String username) {
    return mainUserRepository.findByUsername(username);
  }

  @Override
  public Organisation findById(Long id) throws Throwable {
    return (Organisation) mainUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(("There is no User with such ID")));
  }

  @Override
  public Organisation saveUser(Organisation organisation) throws UserIsNullException {
    organisation.setPassword(encoder.encode(organisation.getPassword()));
    checkIfUserIsnull(organisation);
    return (Organisation) mainUserRepository.save(organisation);
  }

  @Override
  public void removeUser(Long id) throws UserNotFoundException {
    if (!mainUserRepository.existsById(id)) {
      throw new UserNotFoundException("There is no User with such ID");
    }
    mainUserRepository.deleteById(id);
  }

  @Override
  public void checkIfUserIsnull(Organisation organisation) throws UserIsNullException {
    if (organisation == null) {
      throw new UserIsNullException("User must not be null");
    }
  }

  @Override
  public Set<Animal> animalsOwnedByUser(Long userId) throws Throwable {
    return findById(userId).getOwnedAnimalsByUser();
  }

  @Override
  public void addAnimalToAnimalsOwnedByUser(Animal animal, Organisation organisation) throws Throwable {
    Set<Animal> animalsOwnedByUser = animalsOwnedByUser(organisation.getId());
    AnimalFactory animalFactory = new AnimalFactory();
    animalsOwnedByUser.add(animalFactory.create(AnimalType.valueOf(animal.getType())));
    organisation.setOwnedAnimalsByUser(animalsOwnedByUser);
    saveUser(organisation);
  }

  @Override
  public Organisation registerNewUser(Organisation organisation) throws UsernameTakenException {
    if (!mainUserRepository.existsByUsername(organisation.getUsername())) {
      return (Organisation) mainUserRepository.save(organisation);
    }
    throw new UsernameTakenException("Username already taken, please choose an other one.");
  }

  @Override
  public Optional<Organisation> getUserFromAuth(Authentication authenticationn) {
    return Optional.empty();
  }
}

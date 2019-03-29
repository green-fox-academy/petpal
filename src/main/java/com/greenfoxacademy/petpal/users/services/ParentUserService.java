package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.services.AnimalService;
import com.greenfoxacademy.petpal.exception.*;
import com.greenfoxacademy.petpal.geocode.GeoCodeService;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.greenfoxacademy.petpal.users.repositories.ParentUserRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "userDetailsService")
public abstract class ParentUserService<T extends ParentUser> implements UserDetailsService {

  @Autowired
  private ParentUserRepository<ParentUser> parentUserRepository;
  @Autowired
  private AnimalService animalService;
  @Autowired
  private GeoCodeService locationService;

  public abstract String login(T t) throws UserNotFoundException;

  public abstract T register(T t) throws EmailTakenException, UnirestException;

  public abstract T changeUserDetails(T t);

  public ParentUser findByEmail(String email) throws Throwable {
    //TODO: set default message in the constructor of the exception class
    if (parentUserRepository.findByEmail(email) == null) {
      throw new UsernameNotFoundException("There is no User with such email");
    }
    return parentUserRepository.findByEmail(email);
  }

  public ParentUser findById(Long id) throws Throwable {
    //TODO: set default message in the constructor of the exception class
    return parentUserRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
  }

  public ParentUser saveUser(ParentUser t) {
    System.out.println(t.getId());
    return (ParentUser) parentUserRepository.save(t);
  }

  public void removeUser(ParentUser parentUser) {
    parentUserRepository.delete(parentUser);
  }

  public boolean isUserNull(T t) {
    return t == null;
  }

/*  public boolean isUserInDB(T t){
    return mainUserRepository.existsById(t.getId());
  }*/

  public boolean isEmailInDB(T t) {
    return parentUserRepository.existsByEmail(t.getEmail());
  }

  public ParentUser getUserFromAuth(Authentication authentication) throws Throwable {
    return findByEmail((authentication.getName()));
  }

  public Set<Animal> findAllAdoptableAnimals(T t) {
    Set<Animal> allAnimals = animalService.findAll();
    Set<Animal> adoptableAnimals = new HashSet<>();
    for (Animal animal : allAnimals) {
      if ((!isAnimalOwnedByUser(animal, t)) && (isAdoptable(animal))) {
        adoptableAnimals.add(animal);
      }
    }
    return adoptableAnimals;
  }

  public Boolean isAnimalOwnedByUser(Animal animal, T t) {
    return animal.getOwner().equals(t);
  }

  public Boolean isAdoptable(Animal animal) {
    return !animal.getUnderAdoption();
  }

  public Set<Animal> animalsOwnedByUser(T t) {
    return t.getAnimalsOwnedByUser();
  }

  public Set<Animal> animalsLikedByUser(T t) {
    return t.getAnimalsLikedByUser();
  }

  public Set<Animal> animalsUnderAdoptionByUser(T t) {
    Set<Animal> earlierAdoptedAnimals = t.getAnimalsUnderAdoptionByUser();
    Set<Animal> filteredSetAdoptedAnimals = earlierAdoptedAnimals.stream()
            .filter(Animal::getUnderAdoption)
            .collect(Collectors.toSet());
    t.setAnimalsUnderAdoptionByUser(filteredSetAdoptedAnimals);
    return filteredSetAdoptedAnimals;
  }

  public void addAnimalToAnimalsLikedByUser(Animal animal, T t) throws AnimalUnderAdoptionException, AnimalIsNullException {
    if (animal.getUnderAdoption()) {
      throw new AnimalUnderAdoptionException();
    }
    Set<Animal> animalsLikedByUser = t.getAnimalsLikedByUser();
    animalsLikedByUser.add(animal);
    t.setAnimalsLikedByUser(animalsLikedByUser);

    Set<ParentUser> allUsersLiked = animal.getParentUserLike();
    allUsersLiked.add(t);
    animal.setParentUserLike(allUsersLiked);

    saveUser(t);

  }

  public void addAnimalToAnimalsUnderAdoptionByUser(Animal animal, T t) throws ExceedMaxNumberOfAnimalsToAdoptException, OwnedAnimalCannotBeAdoptedException {
    if (animalsUnderAdoptionByUser(t).size() >= 3) {
      throw new ExceedMaxNumberOfAnimalsToAdoptException();
    }
    if (isAnimalOwnedByUser(animal, t)) {
      throw new OwnedAnimalCannotBeAdoptedException();
    }
    Set<Animal> animalsUnderAdoptionByUser = t.getAnimalsUnderAdoptionByUser();
    animalsUnderAdoptionByUser.add(animal);
    t.setAnimalsUnderAdoptionByUser(animalsUnderAdoptionByUser);

    animal.setParentUserAdopt(t);
    animal.setUnderAdoption(true);

    saveUser(t);
  }

  public void addAnimalToAnimalsOwnedByUser(Animal animal, T t) throws AnimalIsNullException {
    Set<Animal> animalsOwnedByUser = t.getAnimalsOwnedByUser();
    animalsOwnedByUser.add(animal);

    animal.setOwner(t);
    animalService.save(animal);

    saveUser(t);
  }

  public void removeAnimalFromAnimalsLikedByUser(Animal animal, T t) throws AnimalIdNotFoundException {

    Set<Animal> animalsLikedByUser = t.getAnimalsLikedByUser();
    if (animalsLikedByUser.contains(animal)) {
      animalsLikedByUser.remove(animal);
      t.setAnimalsLikedByUser(animalsLikedByUser);

      Set<ParentUser> allUsersLiked = animal.getParentUserLike();
      allUsersLiked.remove(t);
      animal.setParentUserLike(allUsersLiked);

      saveUser(t);
    } else {
      throw new AnimalIdNotFoundException();
    }
  }

  public void removeAnimalFromAnimalsUnderAdoptionByUser(Animal animal, T t) {
    Set<Animal> animalsUnderAdoptionByUser = t.getAnimalsUnderAdoptionByUser();
    animalsUnderAdoptionByUser.remove(animal);
    t.setAnimalsUnderAdoptionByUser(animalsUnderAdoptionByUser);

    animal.setParentUserAdopt(null);
    animal.setUnderAdoption(false);

    saveUser(t);
  }

  public void removeAnimalFromAnimalsOwnedByUser(Animal animal, T t) throws AnimalIdNotFoundException, NotOwnedAnimalDeleteException {
    if (!isAnimalOwnedByUser(animal, t)) {
      throw new NotOwnedAnimalDeleteException();
    }
    Set<Animal> animalsOwnedByUser = t.getAnimalsOwnedByUser();
    animalsOwnedByUser.remove(animal);
    t.setAnimalsOwnedByUser(animalsOwnedByUser);

    animal.setOwner(null);
    animalService.remove(animal);

    saveUser(t);
  }
}

package com.greenfoxacademy.petpal.users.services;


import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.exception.EmailTakenException;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;
import com.greenfoxacademy.petpal.users.models.Organisation;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Set;

public class OrganisationServiceImpl extends ParentUserService<Organisation> {
  @Override
  public String login(Organisation organisation) throws UserNotFoundException {
    return null;
  }

  @Override
  public Organisation register(Organisation organisation) throws EmailTakenException, UnirestException {
    return null;
  }

  @Override
  public Set<Animal> animalsLikedByUser(Organisation organisation) {
    return null;
  }

  @Override
  public Set<Animal> animalsToAdoptByUser(Organisation organisation) {
    return null;
  }

  @Override
  public void addAnimalToAnimalsLikedByUser(Animal animal, Organisation organisation) {

  }

  @Override
  public void addAnimalToAnimalsToAdoptByUser(Animal animal, Organisation organisation) {

  }

  @Override
  public void addAnimalToAnimalsOwnedByUser(Animal animal, Organisation organisation) {

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }




  /*
  @Override
  public void addAnimalToAnimalsOwnedByUser(Animal animal, Organisation organisation) throws Throwable {
    Set<Animal> animalsOwnedByUser = animalsOwnedByUser(organisation.getId());
    AnimalFactory animalFactory = new AnimalFactory();
    animalsOwnedByUser.add(animalFactory.create(AnimalType.valueOf(animal.getAnimalRace())));
    organisation.setOwnedAnimalsByUser(animalsOwnedByUser);
    saveUser(organisation);
  }

  */

/*
  @Override
  public Organisation registerNewUser(Organisation organisation) throws EmailTakenException {
    if (!mainUserRepository.existsByEmail(organisation.getEmail())) {
      return (Organisation) mainUserRepository.save(organisation);
    }
    throw new EmailTakenException("Username already taken, please choose an other one.");
  }
*/

}

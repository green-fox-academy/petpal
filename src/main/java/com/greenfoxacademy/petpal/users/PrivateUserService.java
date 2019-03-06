package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import com.greenfoxacademy.petpal.exception.UserIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface PrivateUserService {

  PrivateUser findById(Long id) throws UserIdNotFoundException;
  PrivateUser saveUser(PrivateUser privateUser) throws UserIsNullException;
  void removeUser(Long id) throws UserIdNotFoundException; //return user

  Set<Animal> animalsLikedByUser(Long userId) throws UserIdNotFoundException; //all list set
  Set<Animal> animalsToAdoptByUser(Long userId) throws UserIdNotFoundException;
  List<Animal> ownedAnimalsByUser(Long userId) throws UserIdNotFoundException;
  //+owned

  void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser);
  void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser); //ugzanay mint adopt

  void addAnimalToOwnedAnimalsByUser(Animal animal, PrivateUser privateUser);

//  void markMyAnimalForAdoption(Animal animal, PrivateUser privateUser);

  void checkIfUserIsnull(PrivateUser privateUser) throws UserIsNullException;


}

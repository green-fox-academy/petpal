package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import com.greenfoxacademy.petpal.exception.UserIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface PrivateUserService extends MainUserService<PrivateUser>{





  Set<Animal> animalsLikedByUser(Long userId) throws UserIdNotFoundException; //all list set
  Set<Animal> animalsToAdoptByUser(Long userId) throws UserIdNotFoundException;



  void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser) throws UserIdNotFoundException, UserIsNullException;
  void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser); //ugzanay mint adopt

  void addAnimalToOwnedAnimalsByUser(Animal animal, PrivateUser privateUser);





}

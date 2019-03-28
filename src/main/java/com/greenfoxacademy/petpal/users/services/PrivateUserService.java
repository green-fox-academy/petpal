package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.models.Animal;

import java.util.Set;

public interface PrivateUserService extends MainUserService<PrivateUser> {

  Set<Animal> animalsLikedByUser(Long userId) throws Throwable; //all list set

  Set<Animal> animalsToAdoptByUser(Long userId) throws Throwable;

  void addAnimalToAnimalsLikedByUser(Animal animal, PrivateUser privateUser) throws Throwable;

  void addAnimalToAnimalsToAdoptByUser(Animal animal, PrivateUser privateUser) throws Throwable; //ugzanay mint adopt

}

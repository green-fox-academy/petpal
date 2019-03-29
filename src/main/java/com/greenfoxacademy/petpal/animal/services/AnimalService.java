package com.greenfoxacademy.petpal.animal.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.models.AnimalDTO;
import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;
import com.greenfoxacademy.petpal.exception.InvalidRaceException;
import com.greenfoxacademy.petpal.users.models.ParentUser;

import java.util.Set;

public interface AnimalService {

  Animal save(Animal animal) throws AnimalIsNullException;

  void remove(Animal animal) throws AnimalIdNotFoundException;

  Set<Animal> findAll();

  Animal findById(Long id) throws AnimalIdNotFoundException;

  Animal uploadAnimal(AnimalDTO animalDTO) throws InvalidRaceException, AnimalIsNullException;

  void validateAnimal(Animal animal) throws AnimalIsNullException;

  boolean isAnimalInDB(Animal animal);

  Animal updateAnimalDetails(Long id, ParentUser parentUser, AnimalDTO animalDTO) throws AnimalIdNotFoundException, InvalidRaceException;
}

package com.greenfoxacademy.petpal.animal.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.models.AnimalDTO;
import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;
import com.greenfoxacademy.petpal.exception.InvalidRaceException;

import java.util.Set;

public interface AnimalService {

  Animal save(Animal animal) throws AnimalIsNullException;

  void remove(Long id) throws AnimalIdNotFoundException;

  Set<Animal> findAll();

  Animal findById(Long id) throws AnimalIdNotFoundException;

  Animal uploadAnimal(AnimalDTO animalDTO) throws InvalidRaceException, AnimalIsNullException;

  void validateAnimal(Animal animal) throws AnimalIsNullException;

  boolean isAnimalInDB(Animal animal);

}

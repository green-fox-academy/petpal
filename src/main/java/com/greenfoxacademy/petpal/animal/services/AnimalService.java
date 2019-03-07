package com.greenfoxacademy.petpal.animal.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;

import java.util.List;

public interface AnimalService {

  Animal save(Animal animal) throws AnimalIsNullException;

  void remove(Long id) throws AnimalIdNotFoundException;

  List<Animal> findAll();

  Animal findById(Long id) throws AnimalIdNotFoundException;
}

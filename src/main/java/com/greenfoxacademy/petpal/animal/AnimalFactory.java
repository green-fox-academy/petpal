package com.greenfoxacademy.petpal.animal;

import com.greenfoxacademy.petpal.AbstractFactory;
import com.greenfoxacademy.petpal.animal.models.Animal;

public class AnimalFactory implements AbstractFactory<Animal, AnimalType> {

  @Override
  public Animal create(AnimalType animalType) {
    return animalType.createAnimal();
  }
}

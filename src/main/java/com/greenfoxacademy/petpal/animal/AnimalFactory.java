package com.greenfoxacademy.petpal.animal;

import com.greenfoxacademy.petpal.Factory;
import com.greenfoxacademy.petpal.animal.models.Animal;

public class AnimalFactory  {

  public static Animal create(AnimalType animalType) {
    return animalType.createAnimal();
  }
}

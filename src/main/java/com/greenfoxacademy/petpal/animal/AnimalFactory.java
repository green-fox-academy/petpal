package com.greenfoxacademy.petpal.animal;

public class AnimalFactory implements AbstractFactory<Animal, AnimalType> {

  @Override
  public Animal create(AnimalType animalType) {
    return animalType.createAnimal();
  }
}

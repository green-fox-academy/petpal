package com.greenfoxacademy.petpal.animal;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.models.Cat;
import com.greenfoxacademy.petpal.animal.models.Dog;

public enum AnimalType {

  dog {
    @Override
    public Animal createAnimal() {
      return new Dog();
    }
  },

  cat {
    @Override
    public Animal createAnimal() {
      return new Cat();
    }
  };

  public Animal createAnimal() {
    return null;
  }
}

package com.greenfoxacademy.petpal.animal;

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

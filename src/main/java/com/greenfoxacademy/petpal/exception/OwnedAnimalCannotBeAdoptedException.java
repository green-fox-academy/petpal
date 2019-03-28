package com.greenfoxacademy.petpal.exception;

public class OwnedAnimalCannotBeAdoptedException extends Exception {
  public OwnedAnimalCannotBeAdoptedException(String message) {
    super (message);
  }
}

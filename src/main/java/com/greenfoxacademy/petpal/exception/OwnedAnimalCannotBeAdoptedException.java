package com.greenfoxacademy.petpal.exception;

public class OwnedAnimalCannotBeAdoptedException extends Exception {
  public OwnedAnimalCannotBeAdoptedException() {
    super ("You cannot adopt your own animal");
  }
}

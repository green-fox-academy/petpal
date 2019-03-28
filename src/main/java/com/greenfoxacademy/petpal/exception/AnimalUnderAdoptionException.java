package com.greenfoxacademy.petpal.exception;

public class AnimalUnderAdoptionException extends Exception {

  public AnimalUnderAdoptionException() {
    super("Animal is under adoption");
  }
}

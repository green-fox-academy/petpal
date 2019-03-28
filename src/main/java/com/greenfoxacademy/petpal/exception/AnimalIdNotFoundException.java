package com.greenfoxacademy.petpal.exception;

public class AnimalIdNotFoundException extends Exception {

  public AnimalIdNotFoundException() {
    super("Animal not found");
  }
}

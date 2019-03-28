package com.greenfoxacademy.petpal.exception;

public class NotOwnedAnimalDeleteException extends Exception {
  public NotOwnedAnimalDeleteException() {
    super("You cannot delete someone else's animal");
  }
}

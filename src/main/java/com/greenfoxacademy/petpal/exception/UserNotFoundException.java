package com.greenfoxacademy.petpal.exception;

public class UserNotFoundException extends Exception {

  public UserNotFoundException() {
    super("User is not found");
  }
}

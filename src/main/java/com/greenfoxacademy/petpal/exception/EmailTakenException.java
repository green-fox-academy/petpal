package com.greenfoxacademy.petpal.exception;

public class EmailTakenException extends Exception {

  public EmailTakenException(String message) {
    super("E-mail address is already taken");
  }
}

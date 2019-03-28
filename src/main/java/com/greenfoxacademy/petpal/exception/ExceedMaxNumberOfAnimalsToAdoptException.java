package com.greenfoxacademy.petpal.exception;

public class ExceedMaxNumberOfAnimalsToAdoptException extends Exception {

  public ExceedMaxNumberOfAnimalsToAdoptException() {
    super("Max number of animals to adopt is exceeded");
  }
}

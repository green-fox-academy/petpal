package com.greenfoxacademy.petpal.exception;

public class ChatIdNotFoundException extends Exception {

  public ChatIdNotFoundException() {
    super("Chat id not found");
  }
}

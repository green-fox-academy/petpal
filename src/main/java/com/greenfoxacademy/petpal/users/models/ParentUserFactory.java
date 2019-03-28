package com.greenfoxacademy.petpal.users.models;

public class ParentUserFactory {

  public static ParentUser create(UserType userType) {
    return userType.createUser();
  }
}
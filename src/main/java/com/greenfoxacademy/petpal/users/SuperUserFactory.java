package com.greenfoxacademy.petpal.users;

public class SuperUserFactory {

  public static SuperUser create(UserType userType) {
    return userType.makeUser();
  }
}

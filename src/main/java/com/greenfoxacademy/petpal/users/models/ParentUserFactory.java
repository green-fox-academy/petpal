package com.greenfoxacademy.petpal.users.models;

import com.greenfoxacademy.petpal.Factory;

public class ParentUserFactory {

  public static ParentUser create(UserType userType) {
    return userType.createUser();
  }
}
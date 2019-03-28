package com.greenfoxacademy.petpal.users.models;

import com.greenfoxacademy.petpal.Factory;

public class ParentUserFactory implements Factory<ParentUser, UserType> {

  public ParentUser create(UserType userType) {
    return userType.createUser();
  }
}
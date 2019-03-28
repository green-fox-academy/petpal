package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.users.models.ParentUser;

public class ParentUserFactory {

  public static ParentUser create(UserType userType) {
    return userType.createUser();
  }
}
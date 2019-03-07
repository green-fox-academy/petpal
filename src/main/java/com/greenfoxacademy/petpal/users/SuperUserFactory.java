package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.AbstractFactory;
import com.greenfoxacademy.petpal.users.models.SuperUser;

public class SuperUserFactory implements AbstractFactory<SuperUser, UserType> {

  @Override
  public SuperUser create(UserType userType) {
    return userType.createUser();
  }
}
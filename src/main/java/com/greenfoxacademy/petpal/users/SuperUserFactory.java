package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.AbstractFactory;

public class SuperUserFactory implements AbstractFactory<SuperUser, UserType> {

  @Override
  public SuperUser create(UserType userType) {
    return userType.createUser();
  }
}

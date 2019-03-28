package com.greenfoxacademy.petpal.users;

public enum UserType {

  Private {
    @Override
    public SuperUser createUser() {
      return new PrivateUser();
    }
  },

  Org {
    @Override
    public SuperUser createUser() {
      return new Organisation();
    }
  };

  public SuperUser createUser() {
    return null;
  }

}

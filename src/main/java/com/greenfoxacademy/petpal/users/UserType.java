package com.greenfoxacademy.petpal.users;

public enum UserType {

  Private {
    public SuperUser createUser() {
      return new PrivateUser();
    }
  },

  Org {
    public SuperUser createUser() {
      return new Organisation();
    }
  };

  public SuperUser createUser() {
    return  null;
  }

}

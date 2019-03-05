package com.greenfoxacademy.petpal.users;

public enum UserType {

  Private {
    public SuperUser makeUser() {
      return new PrivateUser();
    }
  },

  Org {
    public SuperUser makeUser() {
      return new Organisation();
    }
  };

  public SuperUser makeUser() {
    return  null;
  }

}

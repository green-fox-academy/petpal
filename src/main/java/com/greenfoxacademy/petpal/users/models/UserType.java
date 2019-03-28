package com.greenfoxacademy.petpal.users.models;


public enum UserType {

  Private {

    public ParentUser createUser() {
      return new PrivateUser();
    }
  },

  Google{
    public ParentUser createUser(){
      return new GoogleUser();
  }
  };

  public ParentUser createUser() {
    return null;
  }
}

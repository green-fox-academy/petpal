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
  }},

  Org {
    public ParentUser createUser() {
      return new Organisation();
    }
  };

  public ParentUser createUser() {
    return null;
  }

}

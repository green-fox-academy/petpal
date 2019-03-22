package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.users.models.GoogleUser;
import com.greenfoxacademy.petpal.users.models.Organisation;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.greenfoxacademy.petpal.users.models.PrivateUser;

public enum UserType {

  Private {

    public PrivateUser createUser() {
      return new PrivateUser();
    }
  },

  Google{
    public GoogleUser createUser(){
      return new GoogleUser();
  }},

  Org {
    public Organisation createUser() {
      return new Organisation();
    }
  };

  public ParentUser createUser() {
    return null;
  }

}

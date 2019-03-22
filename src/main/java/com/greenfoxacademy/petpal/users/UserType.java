package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.users.models.GoogleUser;
import com.greenfoxacademy.petpal.users.models.Organisation;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.greenfoxacademy.petpal.users.models.PrivateUser;

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

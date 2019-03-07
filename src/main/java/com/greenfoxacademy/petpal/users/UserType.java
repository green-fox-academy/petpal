package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.users.models.Organisation;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import com.greenfoxacademy.petpal.users.models.SuperUser;

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

package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.exception.UserIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;

public interface MainUserService {

  PrivateUser findById(Long id) throws UserIdNotFoundException;
  PrivateUser saveUser(PrivateUser privateUser) throws UserIsNullException;
  void removeUser(Long id) throws UserIdNotFoundException; //return user
}

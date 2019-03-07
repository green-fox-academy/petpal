package com.greenfoxacademy.petpal.security;

import com.greenfoxacademy.petpal.users.models.PrivateUser;

import java.util.Optional;

public interface UserService {

  Optional<PrivateUser> getByUsername(String username);
}
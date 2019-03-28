package com.greenfoxacademy.petpal.security;

import java.util.Optional;

public interface UserService {

  Optional<PrivateUser> getByUsername(String username);
}
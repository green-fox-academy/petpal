package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.users.models.SuperUser;

public interface UserService {
    String signUp(SuperUser user);
}

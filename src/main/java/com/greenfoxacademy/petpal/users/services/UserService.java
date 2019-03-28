package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.users.models.GoogleUser;

public interface UserService {
    String signUp(GoogleUser user);
}

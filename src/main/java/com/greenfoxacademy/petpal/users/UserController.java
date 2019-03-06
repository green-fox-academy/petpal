package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.exception.UserIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

  private PrivateUserService privateUserService;

  @Autowired
  public UserController(PrivateUserService privateUserService) {
    this.privateUserService = privateUserService;
  }

  @PostMapping("/register/user")
  public ResponseEntity registerUser(@Valid @RequestBody PrivateUser privateUser) throws UserIsNullException {
    privateUserService.saveUser(privateUser);
    return ResponseEntity.ok().body(privateUser);
  }

}
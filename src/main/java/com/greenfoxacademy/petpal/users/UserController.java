package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.exception.UserIsNullException;
import org.modelmapper.ModelMapper;
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
    privateUserService.registerNewUser(privateUser);
    ModelMapper modelMapper = new ModelMapper();
    return ResponseEntity.ok().body(modelMapper.map(privateUser, UserDTO.class));
  }

}
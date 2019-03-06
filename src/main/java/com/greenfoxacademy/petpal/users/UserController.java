package com.greenfoxacademy.petpal.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

  PrivateUserService privateUserService;

  @Autowired
  public UserController(PrivateUserService privateUserService) {
    this.privateUserService = privateUserService;
  }


  /* PUT /user/{id} -> user adatainak változatása (edited)*/

  @PostMapping("/register/user")
  public ResponseEntity registerUser(@Valid @RequestBody PrivateUser privateUser) {
    return ResponseEntity.ok().body(privateUser);
  }

  @PostMapping("/register/organization")
  public ResponseEntity registerOrganisation(@Valid @RequestBody Organisation organisation) {
    return ResponseEntity.ok().body(organisation);
  }

  @PutMapping("/user/{id}")
  public ResponseEntity changeUser(@PathVariable Long id) {
    return null;
  }

  @PutMapping("/organisation/{id}")
  public ResponseEntity changeOrg(@PathVariable Long id) {

  }

  @GetMapping("/user/{id}/pets")
  public ResponseEntity pets(@PathVariable Long id) {
    return ResponseEntity.ok(privateUserService.findById(id).getAnimalList());
  }

}

package com.greenfoxacademy.petpal.users.controllers;

import com.greenfoxacademy.petpal.exception.UserIsNullException;
import com.greenfoxacademy.petpal.exception.UsernameTakenException;
import com.greenfoxacademy.petpal.users.models.Organisation;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import com.greenfoxacademy.petpal.users.models.UserDTO;
import com.greenfoxacademy.petpal.users.services.PrivateUserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "https://petpalgf.herokuapp.com/", maxAge = 3600)
public class UserController {

  private PrivateUserService privateUserService;
//  OrganisationService organisationService;

  @Autowired
  public UserController(PrivateUserService privateUserService) {
    this.privateUserService = privateUserService;
//    this.organisationService = organisationService;
  }

  @PostMapping("/register/user")
  public ResponseEntity registerUser(@Valid @RequestBody PrivateUser privateUser) throws UserIsNullException, UsernameTakenException, UnirestException {
    privateUserService.registerNewUser(privateUser);
    ModelMapper modelMapper = new ModelMapper();
    return ResponseEntity.ok().header("Access-Control-Allow-Origin", "*").body(modelMapper.map(privateUser, UserDTO.class));
  }

  @PostMapping("/register/organization")
  public ResponseEntity registerOrganisation(@Valid @RequestBody Organisation organisation) {
    //organisationService.save(organisation);
    return ResponseEntity.ok().body(organisation);
  }

  @PutMapping("/user/{id}")
  public ResponseEntity changePrivateUser(@PathVariable Long id, PrivateUser privateUser) throws Throwable {
    PrivateUser privateUserToChange = privateUserService.findById(id);
    return ResponseEntity.ok(privateUserService.saveUser(privateUser));
  }

  /*@PutMapping("/organisation/{id}")
  public ResponseEntity changeOrganisation(@PathVariable Long id, Organisation organisation) {
    Organisation organisationToChange = organisationService.findById(id);
    return ResponseEntity.ok(organisationService.save(organisationToChange));
    return null;
  }*/

  @GetMapping("/user/pets/liked")
  public ResponseEntity likedPets(Authentication authentication) throws Throwable {
    PrivateUser privateUser = privateUserService.getUserFromAuth(authentication).orElseThrow(Exception::new);
    return ResponseEntity.ok(privateUserService.animalsLikedByUser(privateUser.getId()));
  }

  @GetMapping("/user/pets/adopted")
  public ResponseEntity adoptedPets(Authentication authentication) throws Throwable {
    PrivateUser privateUser = privateUserService.getUserFromAuth(authentication).orElseThrow(Exception::new);
    //return ResponseEntity.ok(privateUserToChange.getAnimalsToAdoptByUser());
    return null;
  }

  @GetMapping("/user/pets/owned")
  public ResponseEntity ownedPets(@PathVariable Long id, Authentication authentication) throws Throwable {
    PrivateUser privateUserToChange = privateUserService.findById(id);
    return ResponseEntity.ok(privateUserToChange.getOwnedAnimalsByUser());
  }

}


package com.greenfoxacademy.petpal.users.controllers;

import com.greenfoxacademy.petpal.exception.EmailTakenException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import com.greenfoxacademy.petpal.users.models.Organisation;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import com.greenfoxacademy.petpal.users.models.UserDTO;
import com.greenfoxacademy.petpal.users.services.ParentUserService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

  private ParentUserService parentUserService;
  private ModelMapper modelMapper;

  @Autowired
  public UserController(ParentUserService parentUserService) {
    this.parentUserService = parentUserService;
  }

  @CrossOrigin
  @PostMapping("/register/user")
  public ResponseEntity registerUser(@Valid @RequestBody PrivateUser privateUser) throws UserIsNullException, EmailTakenException, UnirestException {
    parentUserService.register(privateUser);
    return ResponseEntity.ok(modelMapper.map(privateUser, UserDTO.class));
  }

  @PostMapping("/register/organization")
  public ResponseEntity registerOrganisation(@Valid @RequestBody Organisation organisation) throws UserIsNullException, UnirestException, EmailTakenException {
    parentUserService.register(organisation);
    return ResponseEntity.ok().body(modelMapper.map(organisation, UserDTO.class));
  }

  @PutMapping("/user/{id}")
  public ResponseEntity changePassword(Authentication authentication, @RequestBody String password) throws Throwable {
    ParentUser user = (ParentUser) parentUserService.getUserFromAuth(authentication);
    //TODO: I'm not sure if we need this (lyancsie)
    return ResponseEntity.ok().build();
  }

  /*@PutMapping("/organisation/{id}")
  public ResponseEntity changeOrganisation(@PathVariable Long id, Organisation organisation) {
    Organisation organisationToChange = organisationService.findById(id);
    return ResponseEntity.ok(organisationService.save(organisationToChange));
    return null;
  }*/

  @GetMapping("/pets/liked")
  public ResponseEntity likedPets(Authentication authentication) throws Throwable {
    ParentUser parentUser = parentUserService.getUserFromAuth(authentication);
    return ResponseEntity.ok(parentUserService.animalsLikedByUser(parentUser));
    //TODO: remove raw type
  }

  @GetMapping("/pets/adoptable")
  public ResponseEntity adoptedPets(Authentication authentication) throws Throwable {
    ParentUser parentUser = parentUserService.getUserFromAuth(authentication);
    return ResponseEntity.ok(parentUserService.animalsToAdoptByUser(parentUser));
    //TODO: remove raw type
  }

  @GetMapping("/pets/owned")
  public ResponseEntity ownedPets(Authentication authentication) throws Throwable {
    ParentUser parentUser = parentUserService.getUserFromAuth(authentication);
    return ResponseEntity.ok(parentUserService.animalsOwnedByUser(parentUser));
    //TODO: remove raw type
  }
  //TODO: delete endpoints (deleting from the lists)
}
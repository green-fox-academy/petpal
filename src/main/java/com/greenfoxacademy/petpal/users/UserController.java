package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.exception.UserIdNotFoundException;
import com.greenfoxacademy.petpal.exception.UserIsNullException;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

  private PrivateUserService privateUserService;
  OrganisationService organisationService;

  @Autowired
  public UserController(PrivateUserService privateUserService, OrganisationService organisationService) {
    this.privateUserService = privateUserService;
    this.organisationService = organisationService;
  }

  @PostMapping("/register/user")
  public ResponseEntity registerUser(@Valid @RequestBody PrivateUser privateUser) throws UserIsNullException {
    privateUserService.saveUser(privateUser);
    return ResponseEntity.ok().body(privateUser);
  }

  @PostMapping("/register/organization")
  public ResponseEntity registerOrganisation(@Valid @RequestBody Organisation organisation) {
    //organisationService.save(organisation);
    return ResponseEntity.ok().body(organisation);
  }

  @PutMapping("/privateuser/{id}")
  public ResponseEntity changePrivateUser(@PathVariable Long id, PrivateUser privateUser) throws UserNotFoundException, UserIdNotFoundException, UserIsNullException {
    PrivateUser privateUserToChange = privateUserService.findById(id);
    return ResponseEntity.ok(privateUserService.saveUser(privateUser));
  }

  /*@PutMapping("/organisation/{id}")
  public ResponseEntity changeOrganisation(@PathVariable Long id, Organisation organisation) {
    Organisation organisationToChange = organisationService.findById(id);
    return ResponseEntity.ok(organisationService.save(organisationToChange));
    return null;
  }*/

  @GetMapping("/pets/liked")
  public ResponseEntity likedPets(@PathVariable Long id, Authentication authentication) throws UserIdNotFoundException {
    PrivateUser privateUserToChange = privateUserService.findById(id);
    return ResponseEntity.ok(privateUserToChange.getAnimalsLikedByUser());
  }

  @GetMapping("/pets/adopted")
  public ResponseEntity adoptedPets(@PathVariable Long id, Authentication authentication) throws UserIdNotFoundException {
    PrivateUser privateUserToChange = privateUserService.findById(id);
    return ResponseEntity.ok(privateUserToChange.getAnimalsToAdoptByUser());
  }

  @GetMapping("/pets/owned")
  public ResponseEntity ownedPets(@PathVariable Long id, Authentication authentication) throws UserIdNotFoundException {
    PrivateUser privateUserToChange = privateUserService.findById(id);
    return ResponseEntity.ok(privateUserToChange.getAnimalList());
  }
}
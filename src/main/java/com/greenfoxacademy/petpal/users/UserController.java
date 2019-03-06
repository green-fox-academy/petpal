package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.exception.UserIsNullException;
import com.greenfoxacademy.petpal.exception.UserNotFoundException;
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

  @PostMapping("/register/organization")
  public ResponseEntity registerOrganisation(@Valid @RequestBody Organisation organisation) {
    
    return ResponseEntity.ok().body(organisation);
  }

  @PutMapping("/privateuser/{id}")
  public ResponseEntity changePrivateUser(@PathVariable Long id, PrivateUser privateUser) throws UserNotFoundException {
    PrivateUser privateUserToChange = privateUserService.findById(id);
    return ResponseEntity.ok(privateUserService.saveUser(privateUser));
  }

  @PutMapping("/organisation/{id}")
  public ResponseEntity changeOrganisation(@PathVariable Long id, Organisation organisation) {
    /*Organisation organisationToChange = organisationService.findById(id);
    return ResponseEntity.ok(organisationRepository.save(organisationToChange));*/
    return null;
  }

  //TODO: use liked, adopted, owned lists instead
  @GetMapping("/user/{id}/pets/liked")
  public ResponseEntity likedPets(@PathVariable Long id) throws UserNotFoundException {
    return ResponseEntity.ok(privateUserService.findById(id).getAnimalList());
  }

  @GetMapping("/user/{id}/pets/adopted")
  public ResponseEntity adoptedPets(@PathVariable Long id) throws UserNotFoundException {
    return ResponseEntity.ok(privateUserService.findById(id).getAnimalList());
  }

  @GetMapping("/user/{id}/pets/owned")
  public ResponseEntity ownedPets(@PathVariable Long id) throws UserNotFoundException {
    return ResponseEntity.ok(privateUserService.findById(id).getAnimalList());
  }
}
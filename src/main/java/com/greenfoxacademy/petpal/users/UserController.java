package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.exception.UserIsNullException;
import com.greenfoxacademy.petpal.exception.UsernameTakenException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

@Controller
@EnableWebMvc
@CrossOrigin(origins = "https://petpalgf.herokuapp.com/", maxAge = 3600)
public class UserController {

  private PrivateUserService privateUserService;
//  OrganisationService organisationService;

  @Autowired
  public UserController(PrivateUserService privateUserService) {
    this.privateUserService = privateUserService;
//    this.organisationService = organisationService;
  }

  @GetMapping("/")
  public String home(){
    return "index";
  }

  @PostMapping("/register/user")
  @ResponseBody
  public ResponseEntity registerUser(@Valid @RequestBody PrivateUser privateUser) throws UserIsNullException, UsernameTakenException {
    privateUserService.registerNewUser(privateUser);
    ModelMapper modelMapper = new ModelMapper();
    return ResponseEntity.ok().body(modelMapper.map(privateUser, UserDTO.class));
  }

  @PostMapping("/register/organization")
  @ResponseBody
  public ResponseEntity registerOrganisation(@Valid @RequestBody Organisation organisation) {
    //organisationService.save(organisation);
    return ResponseEntity.ok().body(organisation);
  }

  @PutMapping("/privateuser/{id}")
  @ResponseBody
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

  @GetMapping("/pets/liked")
  @ResponseBody
  public ResponseEntity likedPets(@PathVariable Long id, Authentication authentication) throws Throwable {
    PrivateUser privateUserToChange = privateUserService.findById(id);
    return ResponseEntity.ok(privateUserToChange.getAnimalsLikedByUser());
  }

  @GetMapping("/pets/adopted")
  @ResponseBody
  public ResponseEntity adoptedPets(@PathVariable Long id, Authentication authentication) throws Throwable {
    PrivateUser privateUserToChange = privateUserService.findById(id);
    return ResponseEntity.ok(privateUserToChange.getAnimalsToAdoptByUser());
  }

  @GetMapping("/pets/owned")
  @ResponseBody
  public ResponseEntity ownedPets(@PathVariable Long id, Authentication authentication) throws Throwable {
    PrivateUser privateUserToChange = privateUserService.findById(id);
    return ResponseEntity.ok(privateUserToChange.getOwnedAnimalsByUser());
  }

}
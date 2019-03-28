package com.greenfoxacademy.petpal.animal.controllers;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.models.AnimalDTO;
import com.greenfoxacademy.petpal.animal.models.Cat;
import com.greenfoxacademy.petpal.animal.models.Dog;
import com.greenfoxacademy.petpal.animal.services.AnimalService;
import com.greenfoxacademy.petpal.chat.services.ChatService;
import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;
import com.greenfoxacademy.petpal.exception.InvalidRaceException;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import com.greenfoxacademy.petpal.users.services.ParentUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AnimalController {

  private AnimalService animalService;
  private ChatService chatService;
  private ParentUserService userDetailsService;

  @Autowired

  public AnimalController(AnimalService animalService, ParentUserService userDetailsService, ChatService chatService) {
    this.animalService = animalService;
    this.userDetailsService = userDetailsService;
    this.chatService = chatService;
  }

  @GetMapping("/home/pets")
  public ResponseEntity pets(Authentication authentication) throws Throwable {
    ParentUser parentUser = userDetailsService.getUserFromAuth(authentication);
    return ResponseEntity.ok(userDetailsService.findAllAdoptableAnimals(parentUser));
  }

  @GetMapping("/pet/{id}")
  public ResponseEntity pet(@PathVariable Long id) throws AnimalIdNotFoundException {
    return ResponseEntity.ok(animalService.findById(id));
  }

  @PostMapping("/pet/{id}/like")
  public ResponseEntity addToLike(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = userDetailsService.getUserFromAuth(authentication);
    Animal animal = animalService.findById(id);
    System.out.println(parentUser.getId());
    System.out.println(animal.getId());
    userDetailsService.addAnimalToAnimalsLikedByUser(animal, parentUser);
    //TODO: fix raw type error
    return ResponseEntity.ok().build();
  }

  @PostMapping("/pet/{id}/toAdopt")
  public ResponseEntity addToAdopt(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = userDetailsService.getUserFromAuth(authentication);
//    chatService.createChat(parentUser, animalService.findById(id).getOwner(), animalService.findById(id));
    userDetailsService.addAnimalToAnimalsUnderAdoptionByUser(animalService.findById(id), parentUser);
    //TODO: fix raw type error
    return ResponseEntity.ok().build();
  }

  @PostMapping("/uploadPet")
  public ResponseEntity upload(@RequestBody AnimalDTO animalDTO, Authentication authentication) throws Throwable {
    ParentUser parentUser = userDetailsService.getUserFromAuth(authentication);
    userDetailsService.addAnimalToAnimalsOwnedByUser(animalService.uploadAnimal(animalDTO), parentUser);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/pet/{id}")
  public ResponseEntity change(@PathVariable Long id, Authentication authentication, Animal animal) throws AnimalIdNotFoundException, AnimalIsNullException {
    //TODO: modify an animal's details
    //Get animal from frontend WITH ID
    return ResponseEntity.ok().body(animalService.save(animal));
  }

  @DeleteMapping("/pet/{id}/owned")
  public ResponseEntity deleteFromOwned(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = userDetailsService.getUserFromAuth(authentication);
    Animal animal = animalService.findById(id);
    userDetailsService.removeAnimalFromAnimalsOwnedByUser(animal, parentUser);
    return ResponseEntity.ok().build();

  }

  @DeleteMapping("/pet/{id}/like")
  public ResponseEntity deleteFromLiked(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = userDetailsService.getUserFromAuth(authentication);
    Animal animal = animalService.findById(id);
    userDetailsService.removeAnimalFromAnimalsLikedByUser(animal, parentUser);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("pet/{id}/adoptable")
  public ResponseEntity deleteFromAdopt(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = userDetailsService.getUserFromAuth(authentication);
    Animal animal = animalService.findById(id);
    userDetailsService.removeAnimalFromAnimalsUnderAdoptionByUser(animal, parentUser);
    return ResponseEntity.ok().build();
  }
}
//TODO: reduce duplications

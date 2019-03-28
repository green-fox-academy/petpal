package com.greenfoxacademy.petpal.animal.controllers;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.models.AnimalDTO;
import com.greenfoxacademy.petpal.animal.models.Cat;
import com.greenfoxacademy.petpal.animal.models.Dog;
import com.greenfoxacademy.petpal.animal.services.AnimalService;
import com.greenfoxacademy.petpal.chat.services.ChatService;
import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;
import com.greenfoxacademy.petpal.exception.InvalidTypeException;
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
  private ParentUserService userDetailsService;
  private ChatService chatService;

  @Autowired
  public AnimalController(AnimalService animalService, ParentUserService userDetailsService, ChatService chatService) {
    this.animalService = animalService;
    this.userDetailsService = userDetailsService;
    this.chatService = chatService;
  }

  @GetMapping("/home/pets")
  public ResponseEntity pets() {
    return ResponseEntity.ok(animalService.findAll());
  }

  @GetMapping("/pet/{id}")
  public ResponseEntity pet(@PathVariable Long id) throws AnimalIdNotFoundException {
    return ResponseEntity.ok(animalService.findById(id));
  }

  @PostMapping("/pet/{id}/like")
  public ResponseEntity like(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = userDetailsService.getUserFromAuth(authentication);
    userDetailsService.addAnimalToAnimalsLikedByUser(animalService.findById(id), parentUser);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/pet/{id}/toAdopt")
  public ResponseEntity addToAdopt(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = userDetailsService.getUserFromAuth(authentication);
    userDetailsService.addAnimalToAnimalsToAdoptByUser(animalService.findById(id), parentUser);
    chatService.createChat(parentUser, animalService.findById(id).getOwner(), animalService.findById(id));
    return ResponseEntity.ok().build();
  }

  @PostMapping("/pet")
  public ResponseEntity upload(@RequestBody AnimalDTO animalDTO, Authentication authentication) throws Throwable {
    ParentUser privateUser = userDetailsService.getUserFromAuth(authentication);
    ModelMapper modelMapper = new ModelMapper();
    Animal animal;
    if (animalDTO.getType().equals("dog")) {
      animal = modelMapper.map(animalDTO, Dog.class);
    } else if (animalDTO.getType().equals("cat")) {
      animal = modelMapper.map(animalDTO, Cat.class);
    } else {
      throw new InvalidTypeException("Invalid type");
    }
    animalService.save(animal);
    userDetailsService.addAnimalToAnimalsOwnedByUser(animal, privateUser);
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
    return ResponseEntity.ok(parentUser.getOwnedAnimalsByUser().remove(animal));
  }

  @DeleteMapping("/pet/{id}/like")
  public ResponseEntity deleteFromLiked(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = userDetailsService.getUserFromAuth(authentication);
    Animal animal = animalService.findById(id);
    return ResponseEntity.ok(parentUser.getAnimalsLikedByUser().remove(animal));
  }

  @DeleteMapping("pet/{id}/adoptable")
  public ResponseEntity deleteFromToAdopt(@PathVariable Long id, Authentication authentication) throws Throwable {
    ParentUser parentUser = userDetailsService.getUserFromAuth(authentication);
    Animal animal = animalService.findById(id);
    //return ResponseEntity.ok(privateUser.getAnimalsToAdoptByUser().remove(animal));
    return null;
  }
}
//TODO: reduce duplications

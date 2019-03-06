package com.greenfoxacademy.petpal.animal;

import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;
import com.greenfoxacademy.petpal.users.PrivateUser;
import com.greenfoxacademy.petpal.users.PrivateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnimalController {

  private AnimalService animalService;
  private PrivateUserService privateUserService;

  @Autowired
  public AnimalController(AnimalService animalService, PrivateUserService privateUserService) {
    this.animalService = animalService;
    this.privateUserService = privateUserService;
  }

  /*
POST /register (itt lehet 2 féle endpoint lesz attól függően, hogy userként vagy szervezetként regizel)
POST /login
POST /logout
GET /pets -> listázza a peteket (itt lehet mobilon tinderszerűen, gépen akár paginálva több oldalon a petek)
GET /pet/{id} -> adott pet adatait lehet megnézni
POST /pet/{id}/like -> jelzed, hogy érdeklődsz az állat iránt
POST/pet/{id}/save -> elmented a kedvencek közé
DELETE /pet/{id} -> törölheted az "elkelt" állatot*/

  @GetMapping("/pets")
  public ResponseEntity pets() {
    return ResponseEntity.ok(animalService.findAll());
  }

  @GetMapping("/pet/{id}")
  public ResponseEntity pet(@PathVariable Long id) throws AnimalIdNotFoundException {
    return ResponseEntity.ok(animalService.findById(id));
  }

  @PostMapping("/pet/{id}/like")
  public ResponseEntity like(@PathVariable Long id, Authentication authentication) throws Throwable {
    PrivateUser privateUser = (PrivateUser) authentication.getPrincipal();
    privateUserService.addAnimalToAnimalsLikedByUser(animalService.findById(id), privateUser);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/pet/{id}/toAdopt")
  public ResponseEntity addToAdopt(@PathVariable Long id, Authentication authentication) throws Throwable {
    PrivateUser privateUser = (PrivateUser) authentication.getPrincipal();
    privateUserService.addAnimalToAnimalsToAdoptByUser(animalService.findById(id), privateUser);
    return ResponseEntity.ok().build();
  }

  //POST /pet -> új petet tölthesz fel
  @PostMapping("/pet")
  public ResponseEntity upload(Animal animal, Authentication authentication) throws Throwable {
    PrivateUser privateUser = (PrivateUser) authentication.getPrincipal();
    privateUserService.addAnimalToAnimalsOwnedByUser(animal, privateUser);
    return ResponseEntity.ok().body(animalService.save(animal));
  }

  //PUT /pet/{id} -> ha elcseszted, javíthatod az állat adatait (edited)
  @PutMapping("/pet/{id}")
  public ResponseEntity change(@PathVariable Long id, Authentication authentication, Animal animal) throws AnimalIdNotFoundException, AnimalIsNullException {
    return ResponseEntity.ok().body(animalService.save(animal));
  }

  @DeleteMapping("/pet/{id}/owned")
  public ResponseEntity deleteFromOwned(@PathVariable Long id, Authentication authentication) throws AnimalIdNotFoundException {
    PrivateUser privateUser = (PrivateUser) authentication.getPrincipal();
    Animal animal = animalService.findById(id);
    return ResponseEntity.ok(privateUser.getOwnedAnimalsByUser().remove(animal));
  }

  @DeleteMapping("/pet/{id}/like")
  public ResponseEntity deleteFromLiked(@PathVariable Long id, Authentication authentication) throws AnimalIdNotFoundException {
    PrivateUser privateUser = (PrivateUser) authentication.getPrincipal();
    Animal animal = animalService.findById(id);
    return ResponseEntity.ok(privateUser.getAnimalsLikedByUser().remove(animal));
  }

  @DeleteMapping("pet/{id}/adopt")
  public ResponseEntity deleteFromToAdopt(@PathVariable Long id, Authentication authentication) throws AnimalIdNotFoundException {
    PrivateUser privateUser = (PrivateUser) authentication.getPrincipal();
    Animal animal = animalService.findById(id);
    return ResponseEntity.ok(privateUser.getAnimalsToAdoptByUser().remove(animal));
  }
}
//TODO: reduce duplications

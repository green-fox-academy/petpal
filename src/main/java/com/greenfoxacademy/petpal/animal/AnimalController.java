package com.greenfoxacademy.petpal.animal;

import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;
import com.greenfoxacademy.petpal.users.PrivateUser;
import com.greenfoxacademy.petpal.users.PrivateUserService;
import com.greenfoxacademy.petpal.users.SuperUser;
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
    //TODO: if exists
    return ResponseEntity.ok(animalService.findById(id));
  }

  @PostMapping("/pet/{id}/like")
  public ResponseEntity like(@PathVariable Long id, Authentication authentication) throws AnimalIdNotFoundException {
    PrivateUser privateUser = (PrivateUser) authentication.getPrincipal();
    privateUserService.addAnimalToLikedAnimals(animalService.findById(id), privateUser);
    return null;
  }

  //TODO: like this?
  @PostMapping("/pet/{id}/favourite")
  public ResponseEntity favourite(@PathVariable Long id, Authentication authentication) throws AnimalIdNotFoundException, AnimalIsNullException {
    PrivateUser privateUser = (PrivateUser) authentication.getPrincipal();
    privateUserService.addAnimalToFavouriteAnimals(animalService.findById(id), privateUser);
    //TODO: if animal exists by id
    return null;
  }

  //POST /pet -> új petet tölthesz fel
  @PostMapping("/pet")
  public ResponseEntity upload(Animal animal) throws AnimalIsNullException {
    if (animal != null) {
      //TODO: set the user's animal
      return ResponseEntity.ok().body(animalService.save(animal));
    }
    return null;
  }

  //PUT /pet/{id} -> ha elcseszted, javíthatod az állat adatait (edited)
  @PutMapping("/pet/{id}")
  public ResponseEntity change(@PathVariable Long id, Animal animal) throws AnimalIdNotFoundException {
    Animal animalToChange = animalService.findById(id);
    animalToChange = animal;
    return ResponseEntity.ok().body(animal);
  }

  @DeleteMapping("/pet/{id}")
  public ResponseEntity delete(@PathVariable Long id, Authentication authentication) {
    //TODO: delete animal from 3 all the 3 lists and from animal database as well
    SuperUser superUser = (SuperUser) authentication.getPrincipal();
    return ResponseEntity.ok(superUser.getAnimalList().remove(id.intValue()));
  }
}
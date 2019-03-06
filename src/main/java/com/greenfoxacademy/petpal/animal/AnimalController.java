package com.greenfoxacademy.petpal.animal;

import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;
import com.greenfoxacademy.petpal.users.PrivateUser;
import com.greenfoxacademy.petpal.users.PrivateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnimalController {

  private AnimalService animalService;
  private PrivateUserService privateUserService;

  @Autowired
  public AnimalController(AnimalService animalServiceImpl, PrivateUserService privateUserServiceImpl) {
    this.animalService = animalService;
    this.privateUserService = privateUserServiceImpl;
  }

  /*
DELETE /pet/{id} -> törölheted az "elkelt" állatot*/

  @GetMapping("/pets")
  public ResponseEntity pets() {
    return ResponseEntity.ok(animalService.findAll());
  }

  @GetMapping("pet/{id}")
  public ResponseEntity pet(@PathVariable Long id) throws AnimalIdNotFoundException {
    return ResponseEntity.ok(animalService.findById(id));
  }

  @PostMapping("/pet/{id}/like")
  public ResponseEntity like(@PathVariable Long id, PrivateUser privateUser) throws AnimalIdNotFoundException {
    privateUserService.addAnimalToLikedAnimals(animalService.findById(id), privateUser);
    return null;
  }

  @PostMapping("/pet/{id}/save")
  public ResponseEntity save(@PathVariable Long id) throws AnimalIdNotFoundException, AnimalIsNullException {
    if (animalService.findById(id) != null) {
      return ResponseEntity.ok(animalService.save(animalService.findById(id)));
    } else {
      /*
      //TODO: create new Animal + save?
       */
      return null;
    }
  }

  //POST /pet -> új petet tölthesz fel
  @PostMapping("/pet")
  public ResponseEntity upload() {
    return ResponseEntity.ok().body("a");
  }

  //PUT /pet/{id} -> ha elcseszted, javíthatod az állat adatait (edited)
  @PutMapping("/pet")
  public ResponseEntity change(Animal animal) {
    return null;
  }

  @DeleteMapping("/pet")
  public ResponseEntity delete(Animal animal) {
    return null;
  }

}

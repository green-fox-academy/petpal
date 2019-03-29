package com.greenfoxacademy.petpal.users.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.models.Dog;
import com.greenfoxacademy.petpal.animal.repositories.AnimalRepository;
import com.greenfoxacademy.petpal.animal.services.AnimalServiceImpl;
import com.greenfoxacademy.petpal.users.models.PrivateUser;
import com.greenfoxacademy.petpal.users.repositories.MainUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class UserServiceTest {

  @Mock
  MainUserRepository userRepository;

  private PrivateUserServiceImpl userService;
  private String email;
  private String password;
  private String name;
  private Long id;
  private PrivateUser privateUser;
  private PrivateUser privateUser2;
  private Animal animal;
  private Set<Animal> ownedAnimalSet;
  private Set<Animal> likedAnimalSet;
  private Set<Animal> adoptAnimalSet;

  @Before
  public void init(){
    MockitoAnnotations.initMocks(this);
    userService = new PrivateUserServiceImpl();
    email = "email";
    password = "password";
    name = "name";
    id = 1L;
    privateUser = new PrivateUser();
    privateUser.setName(name);
    privateUser.setPassword(password);
    privateUser.setEmail(email);
    privateUser2 = new PrivateUser();
    animal = new Dog();
    animal.setOwner(privateUser2);
    animal.setUnderAdoption(true);
    ownedAnimalSet = new HashSet<>();
    ownedAnimalSet.add(animal);
    likedAnimalSet = new HashSet<>();
    likedAnimalSet.add(animal);
    adoptAnimalSet = new HashSet<>();
    privateUser2.setAnimalsOwnedByUser(ownedAnimalSet);
    privateUser2.setAnimalsLikedByUser(likedAnimalSet);
    privateUser.setAnimalsUnderAdoptionByUser(adoptAnimalSet);
  }
  
  @Test
  public void isAnimalOwnedByUser_returnsFalse() {
    assertEquals(userService.isAnimalOwnedByUser(animal,privateUser), false);

  }

  @Test
  public void isAnimalOwnedByUser_returnsTrue() {
    assertEquals(userService.isAnimalOwnedByUser(animal,privateUser2), true);

  }

  @Test
  public void isAdoptable_returnsFalse() {
    assertFalse(userService.isAdoptable(animal));
  }

  @Test
  public void animalsOwnedByUser_returnsAnimalSet() {
    assertEquals(userService.animalsOwnedByUser(privateUser2), ownedAnimalSet);
  }

  @Test
  public void animalsLikedByUser_returnsAnimalSet() {
    assertEquals(userService.animalsLikedByUser(privateUser2), likedAnimalSet);

  }

  @Test
  public void animalsUnderAdoptionByUser_returnsAnimalSet() {
    assertEquals(userService.animalsUnderAdoptionByUser(privateUser),adoptAnimalSet);
  }

}
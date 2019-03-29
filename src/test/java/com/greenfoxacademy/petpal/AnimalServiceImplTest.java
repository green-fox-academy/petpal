package com.greenfoxacademy.petpal;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.models.AnimalDTO;
import com.greenfoxacademy.petpal.animal.models.Cat;
import com.greenfoxacademy.petpal.animal.models.Dog;
import com.greenfoxacademy.petpal.animal.repositories.AnimalRepository;
import com.greenfoxacademy.petpal.animal.services.AnimalServiceImpl;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;
import com.greenfoxacademy.petpal.exception.InvalidRaceException;
import com.sun.jdi.InvalidTypeException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AnimalServiceImplTest {

  private AnimalServiceImpl animalServiceImpl;

  @Mock
  private AnimalRepository animalRepository;

  private Animal animal;
  private Animal testDog = new Dog();
  private Animal testCat = new Cat();
  private Set<Animal> testSetOfAnimals = new HashSet<>();
  private AnimalDTO testAnimalDTO = new AnimalDTO();


  @Before
  public void init() {

    MockitoAnnotations.initMocks(this);
    animalServiceImpl = new AnimalServiceImpl(animalRepository);
  }

  @Test(expected = AnimalIsNullException.class)
  public void notValidAnimalThrowException() throws AnimalIsNullException {
    animalServiceImpl.validateAnimal(animal);
  }

  @Test
  public void saveValidBuilding_ReturnsBuilding() throws AnimalIsNullException {
    when(animalRepository.save(testDog)).thenReturn(testDog);
    assertEquals(animalServiceImpl.save(testDog),testDog);
  }

  @Test
  public void findAll_GivesCorrectSetOfAnimals(){
    testSetOfAnimals.add(testDog);
    testSetOfAnimals.add(testCat);
    when(animalServiceImpl.findAll()).thenReturn( Stream.of(testCat,testDog).collect(Collectors.toSet()));
    assertEquals(animalServiceImpl.findAll(),testSetOfAnimals);
  }

  @Test(expected = InvalidRaceException.class)
  public void uploadAnimal_WithWrongType_ThrowException() throws InvalidRaceException {
    testAnimalDTO.setName("Garfield");
    testAnimalDTO.setType("NotACat");
    animalServiceImpl.uploadAnimal(testAnimalDTO);
  }
}

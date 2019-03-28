package com.greenfoxacademy.petpal.animal.services;

import com.greenfoxacademy.petpal.animal.AnimalType;
import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.models.AnimalDTO;
import com.greenfoxacademy.petpal.animal.models.Cat;
import com.greenfoxacademy.petpal.animal.models.Dog;
import com.greenfoxacademy.petpal.exception.InvalidRaceException;
import com.greenfoxacademy.petpal.animal.repositories.AnimalRepository;
import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AnimalServiceImpl implements AnimalService {

  private AnimalRepository animalRepository;

  @Autowired
  public AnimalServiceImpl(AnimalRepository animalRepository) {
    this.animalRepository = animalRepository;
  }

  @Override
  public Animal save(Animal animal) throws AnimalIsNullException {
    validateAnimal(animal);
    return animalRepository.save(animal);
  }

  @Override
  public void remove(Animal animal) throws AnimalIdNotFoundException {
    if (animalRepository.existsById(animal.getId())) {
      animalRepository.deleteById(animal.getId());
    }
    throw new AnimalIdNotFoundException("There is no Animal with such ID");
  }
  // kérdés vissza adja e?
  @Override
  public Set<Animal> findAll() {
    return animalRepository.findAllSet();
  }

  @Override
  public Animal findById(Long id) throws AnimalIdNotFoundException {
    return animalRepository.findById(id)
            .orElseThrow(() -> new AnimalIdNotFoundException(("There is no Animal with such ID")));
  }

  @Override
  public Animal uploadAnimal(AnimalDTO animalDTO) throws InvalidRaceException, AnimalIsNullException {
    ModelMapper modelMapper = new ModelMapper();
    Animal animal;
    if (animalDTO.getType().equals("Dog")) {
      animal = modelMapper.map(animalDTO, Dog.class);
    } else if (animalDTO.getType().equals("Cat")) {
      animal = modelMapper.map(animalDTO, Cat.class);
    } else {
      throw new InvalidRaceException("Invalid animalRace");
    }
    //TODO: reflection
//    for (AnimalType type : AnimalType.values())
//      if (animalDTO.getAnimalRace().equals(type.name())){
//        Class<Animal> cls = Class.forName(type.name());
//        animal = modelMapper.map(animalDTO, Class.forName(type.name()));
//      }
    return save(animal);
  }


  @Override
  public void validateAnimal(Animal animal) throws AnimalIsNullException {
    if (animal == null || !isAnimalInDB(animal)) {
      throw new AnimalIsNullException("Animal must not be null");
    }
  }

  @Override
  public boolean isAnimalInDB(Animal animal) {
    return animalRepository.existsById(animal.getId());
  }

}

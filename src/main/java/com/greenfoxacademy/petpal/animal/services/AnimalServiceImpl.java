package com.greenfoxacademy.petpal.animal.services;

import com.greenfoxacademy.petpal.animal.AnimalFactory;
import com.greenfoxacademy.petpal.animal.AnimalType;
import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.models.AnimalDTO;
import com.greenfoxacademy.petpal.animal.models.Cat;
import com.greenfoxacademy.petpal.animal.models.Dog;
import com.greenfoxacademy.petpal.animal.repositories.AnimalRepository;
import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;
import com.greenfoxacademy.petpal.exception.InvalidRaceException;
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
    } else
    throw new AnimalIdNotFoundException();
  }

  // kérdés vissza adja e?
  @Override
  public Set<Animal> findAll() {
    return animalRepository.findAllSet();
  }

  @Override
  public Animal findById(Long id) throws AnimalIdNotFoundException {
    return animalRepository.findById(id)
            .orElseThrow(AnimalIdNotFoundException::new);
  }

  @Override
  public Animal uploadAnimal(AnimalDTO animalDTO) throws InvalidRaceException, AnimalIsNullException {
    ModelMapper modelMapper = new ModelMapper();

    if ((animalDTO.getType().equalsIgnoreCase("dog")) || (animalDTO.getType().equalsIgnoreCase("cat"))) {
//      Animal animal = AnimalFactory.create(AnimalType.valueOf(animalDTO.getType()));
      Animal animal = modelMapper.map(animalDTO, Dog.class);
      return animal;
//    } else if (animalDTO.getType().equals("Cat")) {
//      Animal animal = new Cat();
//      animal = modelMapper.map(animalDTO, Cat.class);
//      return save(animal);
    } else {
      throw new InvalidRaceException();
    }
    //TODO: reflection
//    for (AnimalType type : AnimalType.values())
//      if (animalDTO.getAnimalRace().equals(type.name())){
//        Class<Animal> cls = Class.forName(type.name());
//        animal = modelMapper.map(animalDTO, Class.forName(type.name()));
//      }
  }

  @Override
  public void validateAnimal(Animal animal) throws AnimalIsNullException {
    if (animal == null) {
      throw new AnimalIsNullException();
    }
  }

  @Override
  public boolean isAnimalInDB(Animal animal) {
    return animalRepository.existsById(animal.getId());
  }

}

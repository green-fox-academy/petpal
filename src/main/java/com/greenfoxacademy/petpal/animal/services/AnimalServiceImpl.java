package com.greenfoxacademy.petpal.animal.services;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.filters.AnimalFilter;
import com.greenfoxacademy.petpal.animal.repositories.AnimalRepository;
import com.greenfoxacademy.petpal.exception.AnimalIdNotFoundException;
import com.greenfoxacademy.petpal.exception.AnimalIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
  public void remove(Long id) throws AnimalIdNotFoundException {
    if (animalRepository.existsById(id)) {
      animalRepository.deleteById(id);
    }
    throw new AnimalIdNotFoundException("There is no Animal with such ID");
  }

  @Override
  public List<Animal> findAll() {
    return animalRepository.findAll();
  }

  @Override
  public Animal findById(Long id) throws AnimalIdNotFoundException {
    return animalRepository.findById(id)
            .orElseThrow(() -> new AnimalIdNotFoundException(("There is no Animal with such ID")));
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

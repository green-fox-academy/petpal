package com.greenfoxacademy.petpal.filters;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.animal.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class FilterService {

  private AnimalService animalService;

  @Autowired
  public FilterService(AnimalService animalService) {
    this.animalService = animalService;
  }

  public List<Animal> filterAnimals(AnimalFilter animalFilter) {
    List<Animal> animals = animalService.findAll();

    return animals.stream()
            .filter(a -> a.getBirthDate().after(animalFilter.getBirthDateMin()))
            .filter(a -> a.getBirthDate().before(animalFilter.getBirthDateMax()))
            .filter(a -> a.getSpayed().equals(animalFilter.getSpayed()))
            .filter(a -> a.getVaccinated().equals(animalFilter.getVaccinated()))
            .filter(a -> a.getGender().equals(animalFilter.getGender()))
            .filter(a -> a.getFromWhenAvailable().after(animalFilter.getFromWhenAvailableMin()))
            .collect(Collectors.toList());

  }
}

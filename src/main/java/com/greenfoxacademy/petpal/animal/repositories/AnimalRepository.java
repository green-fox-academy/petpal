package com.greenfoxacademy.petpal.animal.repositories;

import com.greenfoxacademy.petpal.animal.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}

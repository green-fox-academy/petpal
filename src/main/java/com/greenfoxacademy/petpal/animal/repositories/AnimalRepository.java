package com.greenfoxacademy.petpal.animal.repositories;

import com.greenfoxacademy.petpal.animal.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

      List<Animal> findAllBy();

      default Set<Animal> findAllSet () {
        return new HashSet<>(findAll());
      }
}

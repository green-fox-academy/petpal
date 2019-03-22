package com.greenfoxacademy.petpal.users.repositories;

import com.greenfoxacademy.petpal.users.models.ParentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainUserRepository<T extends ParentUser> extends JpaRepository<T, Long> {

  Optional<T> findByName(String name);

  Optional<T> findById(Long id);

  Boolean existsByEmail(String email);

  //T save(T t);

  T findByEmail(String email);
}

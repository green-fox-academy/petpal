package com.greenfoxacademy.petpal.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivateUserRepository extends JpaRepository<PrivateUser, Long> {
  Optional<PrivateUser> findByUsername(String username);
}

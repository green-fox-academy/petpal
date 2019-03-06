package com.greenfoxacademy.petpal.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateUserRepository extends JpaRepository<PrivateUser, Long> {
}

package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateUserRepository extends JpaRepository<PrivateUser, Long> {

}

package com.greenfoxacademy.petpal.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivateUser extends SuperUser {

  public void adoptAnimal() {

  }

  @Override
  public void giveAnimal() {

  }
}

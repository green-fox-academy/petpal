package com.greenfoxacademy.petpal.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Organisation extends SuperUser {

  @Override
  public void markAnimalForAdoption() {

  }
}

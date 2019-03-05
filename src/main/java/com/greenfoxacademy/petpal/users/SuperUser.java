package com.greenfoxacademy.petpal.users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Getter
@Setter
public abstract class SuperUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String username;
  private String password;
  @Email
  private String email;
  private String phoneNumber;
  private String location;

//@OneToMany(mappedBy = "superUser", cascade = CascadeType.PERSIST)
  //  private List<Animal> animalList;

  public abstract void giveAnimal();

}

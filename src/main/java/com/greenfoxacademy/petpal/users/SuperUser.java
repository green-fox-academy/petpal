package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
public abstract class SuperUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  private String username;
  @NotBlank
  private String password;
  @Email
  private String email;
  private String phoneNumber;
  private Double locationLong;
  private Double locationLat;
//  TODO address fields

  //@OneToMany(mappedBy = "superUser", cascade = CascadeType.PERSIST)
  private List<Animal> animalList;
  @OneToMany(mappedBy = "superUser", cascade = CascadeType.PERSIST)
  private List<Animal> ownedAnimalsByUser;

}

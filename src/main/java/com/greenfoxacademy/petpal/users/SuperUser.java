package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

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

<<<<<<< HEAD
  //@OneToMany(mappedBy = "superUser", cascade = CascadeType.PERSIST)
  private List<Animal> animalList;
=======
  @OneToMany(mappedBy = "superUser", cascade = CascadeType.PERSIST)
  private List<Animal> ownedAnimalsByUser;
>>>>>>> 836653d6c3307dc452205255b13a511ac0298a42


}

package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

  @OneToMany(mappedBy = "superUser", cascade = CascadeType.PERSIST)
  private Set<Animal> ownedAnimalsByUser;

  public SuperUser(@NotBlank String username, @NotBlank String password) {
    this.username = username;
    this.password = password;
  }
}

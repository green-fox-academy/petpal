package com.greenfoxacademy.petpal.users.models;

import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.geocode.GeoCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
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

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "geo_code_id")
  private GeoCode geoCode;
  private String address;
//  TODO address fields

  @OneToMany(mappedBy = "superUser", cascade = CascadeType.PERSIST)
  private Set<Animal> ownedAnimalsByUser;

  public SuperUser(@NotBlank String username, @NotBlank String password) {
    this.username = username;
    this.password = password;
  }
}

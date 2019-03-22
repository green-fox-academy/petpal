package com.greenfoxacademy.petpal.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public abstract class ParentUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  private String name;
  @Email
  private String email;
  private String phoneNumber;
  @Column
  private String imageUrl;


  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "geo_code_id")
  private GeoCode geoCode;
  private String address;
//  TODO address fields

  @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private Set<Animal> ownedAnimalsByUser;

  public ParentUser(@NotBlank String name) {
    this.name = name;
  }
}

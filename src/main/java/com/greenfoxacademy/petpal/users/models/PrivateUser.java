package com.greenfoxacademy.petpal.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfoxacademy.petpal.animal.models.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity(name = "PrivateUser")
@DiscriminatorValue("PrivateUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrivateUser extends SuperUser {

  @ManyToMany(mappedBy = "privateUser", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private Set<Animal> animalsLikedByUser;

  /*@OneToMany(mappedBy = "privateUserAdopt")
  private Set<Animal> animalsToAdoptByUser;*/
}

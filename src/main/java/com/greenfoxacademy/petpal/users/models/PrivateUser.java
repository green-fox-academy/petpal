package com.greenfoxacademy.petpal.users.models;

import com.greenfoxacademy.petpal.animal.models.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "PrivateUser")
@DiscriminatorValue("PrivateUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrivateUser extends SuperUser {

  @ManyToMany(mappedBy = "privateUser")
  private Set<Animal> animalsLikedByUser;

  @OneToMany(mappedBy = "privateUserAdopt")
  private Set<Animal> animalsToAdoptByUser;
}

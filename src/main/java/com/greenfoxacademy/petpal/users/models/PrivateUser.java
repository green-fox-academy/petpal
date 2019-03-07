package com.greenfoxacademy.petpal.users.models;

import com.greenfoxacademy.petpal.animal.models.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrivateUser extends SuperUser {

  @ManyToMany(mappedBy = "privateUser", cascade = CascadeType.PERSIST)
  private Set<Animal> animalsLikedByUser;

  @OneToMany(mappedBy = "privateUserAdopt", cascade = CascadeType.PERSIST)
  private Set<Animal> animalsToAdoptByUser;
}

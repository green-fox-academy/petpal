package com.greenfoxacademy.petpal.users;

import com.greenfoxacademy.petpal.animal.Animal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class PrivateUser extends SuperUser {

  @ManyToMany(mappedBy = "privateUser")
  private Set<Animal> animalsLikedByUser;

  @OneToMany(mappedBy = "privateUserAdopt")
  private Set<Animal> animalsToAdoptByUser;
}

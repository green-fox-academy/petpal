package com.greenfoxacademy.petpal.animal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfoxacademy.petpal.chat.models.Chat;
import com.greenfoxacademy.petpal.users.models.ParentUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "animal_race")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Animal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Timestamp birthDate;
  private String type;
  // private String animalRace;
  private String gender;
  private Timestamp fromWhenAvailable;
  private String photoPath;
  private Boolean spayed;
  private Boolean vaccinated;
  private Boolean underAdoption;

  @ManyToMany
  @JoinTable(
          name = "parent_users_liked_animals",
          joinColumns = @JoinColumn(
                  name = "animal_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(
                  name = "parent_user_id", referencedColumnName = "id"))
  private Set<ParentUser> parentUserLike;

  @ManyToOne
  @JoinColumn(name = "adopter_id", referencedColumnName = "id")
  private ParentUser parentUserAdopt;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "owner_id", referencedColumnName = "id")
  private ParentUser owner;

  @OneToOne(mappedBy = "animal", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private Chat chat;
}

package com.greenfoxacademy.petpal.animal.models;

import com.greenfoxacademy.petpal.users.models.ParentUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "animal_type")
@Getter
@Setter
public abstract class Animal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Timestamp birthDate;
  private String type;
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

  protected Long getAge() {
    return 0L;
  }
}

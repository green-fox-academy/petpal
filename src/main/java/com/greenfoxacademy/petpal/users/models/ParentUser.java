package com.greenfoxacademy.petpal.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfoxacademy.petpal.animal.models.Animal;
import com.greenfoxacademy.petpal.chat.models.Chat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ParentUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Email
  @NotBlank
  private String email;
  private String phoneNumber;
  @Column
  private String imageUrl;

  /*  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "geo_code_id")
    private GeoCode geoCode;*/
  private String address;
//  TODO address fields

  @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private Set<Animal> animalsOwnedByUser;

  @ManyToMany(mappedBy = "parentUserLike", cascade = CascadeType.PERSIST)
  @JsonIgnore

  private Set<Animal> animalsLikedByUser;

  @OneToMany(mappedBy = "parentUserAdopt", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private Set<Animal> animalsUnderAdoptionByUser;

  @ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private Set<Chat> chats;
}

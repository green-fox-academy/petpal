package com.greenfoxacademy.petpal.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity(name = "PrivateUser")
@DiscriminatorValue("PrivateUser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrivateUser extends ParentUser {

  @NotBlank
  @JsonIgnore
  private String password;

}

package com.greenfoxacademy.petpal.users.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO {
  @NotBlank
  private String email;
  @NotBlank
  private String password;
}

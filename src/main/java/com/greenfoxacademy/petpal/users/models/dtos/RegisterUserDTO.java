package com.greenfoxacademy.petpal.users.models.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserDTO {

  @NotBlank
  private String name;
  @NotBlank
  private String email;
  @NotBlank
  private String password;
}

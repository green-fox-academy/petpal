package com.greenfoxacademy.petpal.users.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity(name = "Organization")
@DiscriminatorValue("Organization")
@Getter
@Setter
public class Organisation extends ParentUser {

    @NotBlank
    private String password;

}

package com.greenfoxacademy.petpal.animal.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Dog")
@DiscriminatorValue("Dog")
@Getter
@Setter
@NoArgsConstructor
public class Dog extends Animal {

}

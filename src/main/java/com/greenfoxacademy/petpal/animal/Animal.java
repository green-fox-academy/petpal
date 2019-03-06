package com.greenfoxacademy.petpal.animal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
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
  //TODO Oltások? Ivartalanítva van vagy sem? Fotó?

  protected Long getAge() {
    return 0L;
  }

}

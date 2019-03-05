package com.greenfoxacademy.petpal.animal;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public abstract class Animal {

  private Long id;
  private String name;
  private Timestamp birthDate;
  private String type;
  private String gender;
  private Timestamp fromWhenAvailable;

  protected Long getAge() {
    return 0L;
  }

}

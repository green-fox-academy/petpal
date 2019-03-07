package com.greenfoxacademy.petpal.filters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class AnimalFilter {
  private Timestamp birthDateMin;
  private Timestamp birthDateMax;
  private List<String> types;
  private String gender;
  private Timestamp fromWhenAvailableMin;
  private Boolean spayed;
  private Boolean vaccinated;

  public AnimalFilter() throws ParseException {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    this.birthDateMin = new Timestamp(currentTime.getTime() - TimeUnit.MINUTES.toMillis(10518960));
    this.birthDateMax = new Timestamp(currentTime.getTime());
    this.fromWhenAvailableMin = new Timestamp(currentTime.getTime());
  }
}

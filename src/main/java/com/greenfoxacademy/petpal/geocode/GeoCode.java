package com.greenfoxacademy.petpal.geocode;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GeoCode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Double lng;
  private Double lat;

/*  @OneToOne(mappedBy = "geoCode", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private ParentUser parentUser;*/
}

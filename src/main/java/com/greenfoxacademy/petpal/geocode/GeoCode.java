package com.greenfoxacademy.petpal.geocode;

import com.greenfoxacademy.petpal.users.models.SuperUser;
import lombok.*;

import javax.persistence.*;

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

  @OneToOne(mappedBy = "geoCode", cascade = CascadeType.PERSIST)
  private SuperUser superUser;
}

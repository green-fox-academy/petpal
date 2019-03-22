package com.greenfoxacademy.petpal.geocode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoLocationController {

  private GeoCodeService geoCodeService;

  @Autowired
  public GeoLocationController(GeoCodeService geoCodeService) {
    this.geoCodeService = geoCodeService;
  }

  @PostMapping("/calcloc")
  public ResponseEntity calcGeoLoc() {
    GeoCode userGeoCode = new GeoCode();
    GeoCode animalGeoCode = new GeoCode();

    userGeoCode.setLat(51.5007292);
    userGeoCode.setLng(-0.1268194);
    animalGeoCode.setLat(47.559802);
    animalGeoCode.setLng(19.095713);

    return ResponseEntity.ok(geoCodeService.calculateDistance(userGeoCode, animalGeoCode));
  }
}
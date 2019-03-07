package com.greenfoxacademy.petpal.geolocation;

import org.springframework.stereotype.Service;

@Service
public class GeoLocationService {

  public Double calculateDistance(Location userLocation, Location animalLocation) {
    Double AVERAGE_RADIUS_OF_EARTH_KM = 6371D;

    Double latDistance = Math.toRadians(userLocation.getLat() - animalLocation.getLat());
    Double lngDistance = Math.toRadians(userLocation.getLng() - animalLocation.getLng());
    Double sinLat = Math.sin(latDistance / 2);
    Double sinLng = Math.sin(lngDistance / 2);
    Double a = sinLat * sinLat +
            (Math.cos(Math.toRadians(userLocation.getLat())) *
                    Math.cos(Math.toRadians(animalLocation.getLat())) *
                    sinLng * sinLng);
    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return AVERAGE_RADIUS_OF_EARTH_KM * c;
  }
}

package com.greenfoxacademy.petpal.geolocation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class GeoLocationController {
  private GeoLocationService geoLocationService;

  @Autowired
  public GeoLocationController(GeoLocationService geoLocationService) {
    this.geoLocationService = geoLocationService;
  }

  @GetMapping("/geoloc")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity getCountryCodes() throws UnirestException, IOException {
    HttpResponse<JsonNode> response = Unirest.get(
            "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyCPcfqgf7iuZQuD8meWVgf8pqZnPtGuCKU")
            .asJson();

    JSONObject jsonObject = response.getBody().getObject();
    JSONArray jsonArray = jsonObject.getJSONArray("results");
    JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
    JSONObject geometry = jsonObject1.getJSONObject("geometry");
    JSONObject location = geometry.getJSONObject("location");
    Double lng = (Double) location.get("lng");
    Double lat = (Double) location.get("lat");

    return ResponseEntity.ok(lng + " " + lat);
  }

  @PostMapping("/calcloc")
  public ResponseEntity calcGeoLoc() {
    Location userLocation = new Location();
    Location animalLocation = new Location();

    userLocation.setLat(51.5007292);
    userLocation.setLng(-0.1268194);
    animalLocation.setLat(47.559802);
    animalLocation.setLng(19.095713);

    return ResponseEntity.ok(geoLocationService.calculateDistance(userLocation, animalLocation));
  }
}

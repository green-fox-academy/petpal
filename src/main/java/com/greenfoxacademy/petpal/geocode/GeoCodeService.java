package com.greenfoxacademy.petpal.geocode;

import com.greenfoxacademy.petpal.users.models.SuperUser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class GeoCodeService {

  public GeoCode generateUserLocationFromAddress(SuperUser superUser) throws UnirestException {
    String prefix = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    String address = superUser.getAddress();
    String suffix = "&key=" + System.getenv("GOOGLE_API_KEY");
    HttpResponse<JsonNode> response = Unirest.get(prefix + address + suffix).asJson();

    return createLocationObject(response);
  }

  private JSONObject parseJsonLocation(HttpResponse<JsonNode> response) {
    JSONObject jsonObject = response.getBody().getObject();
    JSONArray resultsArray = jsonObject.getJSONArray("results");
    JSONObject results = (JSONObject) resultsArray.get(0);
    JSONObject geometry = results.getJSONObject("geometry");
    return geometry.getJSONObject("location");
  }

  private GeoCode createLocationObject(HttpResponse<JsonNode> response) {
    JSONObject location = parseJsonLocation(response);
    return GeoCode.builder()
            .lat((Double) location.get("lng"))
            .lng((Double) location.get("lat"))
            .build();
  }

  public Double calculateDistance(GeoCode userGeoCode, GeoCode animalGeoCode) {
    Double AVERAGE_RADIUS_OF_EARTH_KM = 6371D;

    Double latDistance = Math.toRadians(userGeoCode.getLat() - animalGeoCode.getLat());
    Double lngDistance = Math.toRadians(userGeoCode.getLng() - animalGeoCode.getLng());
    Double sinLat = Math.sin(latDistance / 2);
    Double sinLng = Math.sin(lngDistance / 2);
    Double a = sinLat * sinLat +
            (Math.cos(Math.toRadians(userGeoCode.getLat())) *
                    Math.cos(Math.toRadians(animalGeoCode.getLat())) *
                    sinLng * sinLng);
    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return AVERAGE_RADIUS_OF_EARTH_KM * c;
  }

}

package instaWeather;

import java.util.Map;

public class GetCoordinates {
  private String address;
  
  public Map<String, Double> coords;
  
  public GetCoordinates(String address) {
    this.address = address;
    coords = openStreetMapsGeoLocator.getInstance().getCoordinates(this.address);
  }
  
  public double getLatitude() {
   return coords.get("lat");
  }
  
  public double getLogitude() {
    return coords.get("lon");
  }
}

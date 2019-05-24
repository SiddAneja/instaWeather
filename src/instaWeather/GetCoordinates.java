package instaWeather;

import java.util.Map;

public class GetCoordinates {
  private String address;
  
  public Map<String, Double> coords;
  
  public GetCoordinates(String address) {
    this.address = address;
    this.coords = openStreetMapsGeoLocator.getInstance().getCoordinates(this.address);
  }
  
  public Double getLatitude() {
   return coords.get("lat");
  }
  
  public Double getLogitude() {
    return coords.get("lon");
  }
}

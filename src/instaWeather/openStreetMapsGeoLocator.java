package instaWeather;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class openStreetMapsGeoLocator {
  public final static Logger log = Logger.getLogger("openStreetMapGeoLocator");
  
  private static openStreetMapsGeoLocator instance = null;
  
  protected JSONParser jParse;
  
  //Constructor no longer creates instance
  public openStreetMapsGeoLocator() {
    jParse = new JSONParser();
  }
  
  //Switched to static method
  public static openStreetMapsGeoLocator getInstance() {
    if(instance == null) {
      instance = new openStreetMapsGeoLocator();
    }
    return instance;
  }
  
  public String sendRequest(String url) throws Exception{
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    
    //Added a branch to check if the connection is invalid
    if(con.getResponseCode() != 200) {
      return null;
    }
    
    BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();
    while((inputLine = input.readLine()) != null) {
      response.append(inputLine);
    }
    input.close();
    return response.toString();
  }
  
  public Map<String, Double> getCoordinates(String address) {
    Map<String, Double> coordinates;
    StringBuffer query;
    String[] splitAddress = address.split(" ");
    String searchQuery = null;
    if(splitAddress.length == 0) {
      return null;
    }
    query = new StringBuffer();
    coordinates = new HashMap<String, Double>();
    
    query.append("http://nominatim.openstreetmap.org/search?q=");
    
    for (int i = 0; i < splitAddress.length; i++) {
      query.append(splitAddress[i]);
      if (i < (splitAddress.length - 1)) {
          query.append("+");
      }
    }
    query.append("&format=json&addressdetails=1");
    
    log.debug("Query:" + query);
    
    try {
      searchQuery = sendRequest(query.toString());
    }
    catch(Exception e) {
      log.error("Error when trying to get data with the following query " + query);
    }
    
    if(searchQuery == null) {
      return null;
    }
    
    Object objt = JSONValue.parse(searchQuery);
    log.debug("obj=" + objt);
    
    if (objt instanceof JSONArray) {
      JSONArray array = (JSONArray) objt;
      if (array.size() > 0) {
          JSONObject jsonObject = (JSONObject) array.get(0);

          String lon = (String) jsonObject.get("lon");
          String lat = (String) jsonObject.get("lat");
          log.debug("lon=" + lon);
          log.debug("lat=" + lat);
          coordinates.put("lon", Double.parseDouble(lon));
          coordinates.put("lat", Double.parseDouble(lat));

      }
    }
    return coordinates;
  }
}

package instaWeather;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class getWeather {
  private final static String APIKey= "aaae08ccaba42053a34c43473aaba97d"; 
  
  private static getWeather instance = null;
  
  protected JSONParser jParse;
  
  public getWeather() {
    jParse = new JSONParser();
  }
  
  public static getWeather getInstance() {
    if(instance == null) {
      instance = new getWeather();
    }
    return instance;
  }
  
  private String getRequest(String url) throws Exception{
    final URL obj = new URL(url);
    final HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.connect();
    
    //Added a branch to check if the connection is invalid
    if(con.getResponseCode() != 200) {
      System.out.println(con.getResponseCode());
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
  
  private JSONObject callToAPI(String address) {
    String searchQuery = null;
    GetCoordinates location = new GetCoordinates(address);
    Double latitude = location.getLatitude();
    Double longitude = location.getLogitude();
    String query = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude+ "&APPID=";
    query = query + APIKey;
    
    try {
      searchQuery = getRequest(query);
    }
    catch(Exception e) {
      System.out.println("Error when trying to get data with the following query " + query);
    }
    if(searchQuery == null) {
      return null;
    }
    
    JSONObject objt = (JSONObject) JSONValue.parse(searchQuery);
    return objt;
  }
  
  public Double getTemperatureCelsius(String address) {
    JSONObject jsonObject = getInstance().callToAPI(address);
    if(jsonObject == null) {
      return null;
    }
    JSONObject main = (JSONObject) jsonObject.get("main");
    Double temperature = (Double) main.get("temp");
    return (temperature - 273.15);
  }
  
  public Double getTemperatureFahrenheit(String address) {
    Double temperature = this.getTemperatureCelsius(address);
    return ((temperature * 9/5) + 32);
  }
  
  public String getIcon(String address) {
    JSONObject jsonObject = getInstance().callToAPI(address);
    if(jsonObject == null) {
      return null;
    }
    JSONArray weather = (JSONArray) jsonObject.get("weather");
    JSONObject obj = (JSONObject) weather.get(0);
    String icon = (String) obj.get("icon");
    return icon;
  }
}

package Parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser {

    private static String description;
    private static double temp;


    public static void parse(String WeatherData) {
        try {
            parseLibrary(WeatherData);
        } catch (JSONException e) {
            System.out.println("Error parsing JSON data");
            e.printStackTrace();
        }
    }

    private static void parseLibrary(String jsonData) throws JSONException {
        JSONArray library = new JSONArray(jsonData);

        for (int index = 0; index < library.length(); index++) {
            JSONObject Weather = library.getJSONObject(index);
            parseWeather(Weather);
        }
    }

    private static void parseWeather(JSONObject Weather) throws JSONException {
        JSONObject WeatherParsed = (JSONObject) Weather.getJSONArray("weather").get(0);
        description = WeatherParsed.getString("main");
        JSONObject tempParsed = Weather.getJSONObject("main");
        temp = Math.round((tempParsed.getDouble("temp") - 273.15) * 10.0) / 10.0;
    }

    public static String getDescription() {
        return description;
    }

    public static double getTemp() {
        return temp;
    }
}

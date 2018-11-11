package com.example.pluscomputers.weatherapp.utilities;

import android.util.Log;

import com.example.pluscomputers.weatherapp.model.Weather;
import com.example.pluscomputers.weatherapp.model.Days;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherJsonUtils {

    private static ArrayList<Days> listDays = new ArrayList<>();

    public static Weather getWeather(String response) {

        try {
            JSONObject rootObj = new JSONObject(response);
            JSONObject dataObj = rootObj.getJSONObject("data");

            //time_zone
            JSONArray time_zoneArray = dataObj.getJSONArray("time_zone");
            JSONObject time_zoneObj = time_zoneArray.getJSONObject(0);
            String localTime = time_zoneObj.getString("localtime");

            //current_condition
            JSONArray current_conditionArray = dataObj.getJSONArray("current_condition");
            JSONObject current_conditionObj = current_conditionArray.getJSONObject(0);
            String tempC = current_conditionObj.getString("temp_C");

            JSONArray weatherIconUrlArray = current_conditionObj.getJSONArray("weatherIconUrl");
            JSONObject weatherIconUrlObj = weatherIconUrlArray.getJSONObject(0);
            String weatherIconUrl = weatherIconUrlObj.getString("value");

            JSONArray weatherDescArray = current_conditionObj.getJSONArray("weatherDesc");
            JSONObject weatherDescObj = weatherDescArray.getJSONObject(0);
            String weatherDesc = weatherDescObj.getString("value");

            String windSpeedKmph = current_conditionObj.getString("windspeedKmph");

            String humidity = current_conditionObj.getString("humidity");
            String pressure = current_conditionObj.getString("pressure");
            String feelsLikeC = current_conditionObj.getString("FeelsLikeC");

            // weather
            JSONArray weatherArray = dataObj.getJSONArray("weather");
            for (int i=1 ; i<weatherArray.length() ; i++){
                JSONObject weatherObj = weatherArray.getJSONObject(i);

                String date = weatherObj.getString("date");
                String maxTemp = weatherObj.getString("maxTempC");
                String minTemp = weatherObj.getString("minTempC");

                // hourly
                JSONArray hourlyArray = weatherObj.getJSONArray("hourly");
                JSONObject hourlyObj = hourlyArray.getJSONObject(4);

                JSONArray weatherIconUrlHourlyArray = hourlyObj.getJSONArray("weatherIconUrl");
                JSONObject weatherIconUrlHourlyObj = weatherIconUrlHourlyArray.getJSONObject(0);
                String weatherIconHourlyUrl = weatherIconUrlHourlyObj.getString("value");

                JSONArray weatherDescHourlyArray = hourlyObj.getJSONArray("weatherDesc");
                JSONObject weatherDescHurlyObj = weatherDescHourlyArray.getJSONObject(0);
                String weatherHourlyDesc = weatherDescHurlyObj.getString("value");

                String humidityHourly = hourlyObj.getString("humidity");
                String pressureHourly = hourlyObj.getString("pressure");
                //String windHourly = hourlyObj.getString("WindGustKmph");
                String chanceOfRainHourly = hourlyObj.getString("chanceofrain");

                Days days = new Days(date,maxTemp,minTemp,weatherIconHourlyUrl,weatherHourlyDesc,
                        humidityHourly,pressureHourly,chanceOfRainHourly);

                listDays.add(days);


            }
            Weather weather = new Weather(localTime,tempC,weatherIconUrl,weatherDesc,
                    windSpeedKmph,humidity,pressure,feelsLikeC,listDays);

            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }
}

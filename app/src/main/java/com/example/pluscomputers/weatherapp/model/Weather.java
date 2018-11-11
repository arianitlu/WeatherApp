package com.example.pluscomputers.weatherapp.model;

import java.util.List;

public class Weather {

    private String localTime;
    private String tempC;
    private String weatherIconUrl;
    private String weatherDesc;
    private String windspeedKmph;
    private String humidity;
    private String pressure;
    private String feelsLikeC;
    private List<Days> daysList;

    public Weather() {
    }

    public Weather(String localTime, String tempC, String weatherIconUrl, String weatherDesc, String windspeedKmph, String humidity,
                   String pressure, String feelsLikeC, List<Days> daysList) {
        this.localTime = localTime;
        this.tempC = tempC;
        this.weatherIconUrl = weatherIconUrl;
        this.weatherDesc = weatherDesc;
        this.windspeedKmph = windspeedKmph;
        this.humidity = humidity;
        this.pressure = pressure;
        this.feelsLikeC = feelsLikeC;
        this.daysList = daysList;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public String getTempC() {
        return tempC;
    }

    public void setTempC(String tempC) {
        this.tempC = tempC;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(String weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getWindspeedKmph() {
        return windspeedKmph;
    }

    public void setWindspeedKmph(String windspeedKmph) {
        this.windspeedKmph = windspeedKmph;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getFeelsLikeC() {
        return feelsLikeC;
    }

    public void setFeelsLikeC(String feelsLikeC) {
        this.feelsLikeC = feelsLikeC;
    }

    public List<Days> getHourlyList() {
        return daysList;
    }

    public void setHourlyList(List<Days> daysList) {
        this.daysList = daysList;
    }
}

package com.example.pluscomputers.weatherapp.model;

public class Days {

    private String date;
    private String maxTempC;
    private String minTempC;
    private String weatherIconHourlyUrl;
    private String weatherHourlyDesc;
    private String humidity;
    private String pressure;
    private String chanceOfRain;

    public Days(String date, String maxTempC, String minTempC, String weatherIconHourlyUrl,
                String weatherHourlyDesc, String humidity, String pressure, String chanceOfRain) {
        this.date = date;
        this.maxTempC = maxTempC;
        this.minTempC = minTempC;
        this.weatherIconHourlyUrl = weatherIconHourlyUrl;
        this.weatherHourlyDesc = weatherHourlyDesc;
        this.humidity = humidity;
        this.pressure = pressure;
        this.chanceOfRain = chanceOfRain;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaxTempC() {
        return maxTempC;
    }

    public void setMaxTempC(String maxTempC) {
        this.maxTempC = maxTempC;
    }

    public String getMinTempC() {
        return minTempC;
    }

    public void setMinTempC(String minTempC) {
        this.minTempC = minTempC;
    }

    public String getWeatherIconHourlyUrl() {
        return weatherIconHourlyUrl;
    }

    public void setWeatherIconHourlyUrl(String weatherIconHourlyUrl) {
        this.weatherIconHourlyUrl = weatherIconHourlyUrl;
    }

    public String getWeatherHourlyDesc() {
        return weatherHourlyDesc;
    }

    public void setWeatherHourlyDesc(String weatherHourlyDesc) {
        this.weatherHourlyDesc = weatherHourlyDesc;
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

    public String getChanceOfRain() {
        return chanceOfRain;
    }

    public void setChanceOfRain(String chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }
}

package com.example.pluscomputers.weatherapp.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class  NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    // http://api.worldweatheronline.com/premium/v1/weather.ashx?key=ec6a2189bd7e486588b133648182410
    // &q=Prizren&format=json&num_of_days=5&showlocaltime=yes


    // Keys for URI
    private static final String WEATHER_BASE_URL = "http://api.worldweatheronline.com/premium/v1/weather.ashx";

    private static final String API_KEY_PARAM = "key";
    private static final String apiKey = "ec6a2189bd7e486588b133648182410";

    private static final String CITY_KEY_PARAM = "q";
    private static final String cityKey = "Prizren";

    private static final String FORMAT_KEY = "format";
    private static final String formatKey = "json";

    private static final String NUM_DAYS = "num_of_days";
    private static final String numDaysKey = "5";

    private static final String LOCAL_TIME = "showlocaltime";
    private static final String localTimeKey = "yes";

    public static URL buildWeatherUrl(String city) {
        Uri builtUri = Uri.parse(WEATHER_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, apiKey)
                .appendQueryParameter(CITY_KEY_PARAM,city)
                .appendQueryParameter(FORMAT_KEY,formatKey)
                .appendQueryParameter(NUM_DAYS,numDaysKey)
                .appendQueryParameter(LOCAL_TIME,localTimeKey)
                .build();
        Log.i(TAG,builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Build movies URI: " + url);
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}

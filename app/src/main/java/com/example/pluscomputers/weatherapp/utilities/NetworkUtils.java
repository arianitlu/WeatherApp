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

//     http://api.worldweatheronline.com/premium/v1/weather.ashx?key=ec6a2189bd7e486588b133648182410
//     &q=Prizren&format=json&num_of_days=5&showlocaltime=yes

    // Keys for URI
    private static final String WEATHER_BASE_URL = "http://api.worldweatheronline.com/premium/v1/weather.ashx";

    private static final String API_KEY_PARAM = "key";
    private static final String apiKey = "ec6a2189bd7e486588b133648182410";

    private static final String QUERY = "q";
    private static final String cityKey = "Prizren";

    private static final String FORMAT_KEY = "format";
    private static final String formatKey = "json";

    private static final String NUM_DAYS = "num_of_days";
    private static final String numDaysKey = "5";

    private static final String LOCAL_TIME = "showlocaltime";
    private static final String localTimeKey = "yes";

    /**
     *
     * @param city Japim qytetin si parameter ne varesi se ku deshirojme te formojme nje URL per motin
     * @return  Na kthen nje URL te ndertuar ne baze te qytetit qe deshirojme , URL(API) qe mban te gjitha te dhenat
     * e motit ne formatin JSON
     */
    public static URL buildWeatherCityUrl(String city) {
        Uri builtUri = Uri.parse(WEATHER_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, apiKey)
                .appendQueryParameter(QUERY,city)
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
        Log.v(TAG, "Build weather city URI: " + url);
        return url;
    }

    /**
     *
     * @param latitude Si parameter te pare merr gjatesine
     * @param longitude Si parameter te dyte merr gjeresine
     * @return Na kthen URL ne formatin perkates me koordinatat e caktuar , URL qe do te kete te dhena per motin
     * formatin JSON
     */
    public static URL buildWeatherGpsUrl(long latitude,long longitude) {
        Uri builtUri = Uri.parse(WEATHER_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, apiKey)
                .appendQueryParameter(QUERY,latitude+ "," + longitude)
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
        Log.v(TAG, "Build weather gps URI: " + url);
        return url;
    }

    /**
     *
     * @param url Si paremeter hyres do te jete URL e krijuar me pare ose me qytet ose me GPS(koordinata)
     * @return   Na kthen nje String e cila permban te gjitha te dhenat ne formatin JSON ne menyre qe ne te mund
     * te bejme parsimin dhe te marrim fushat e caktuara per motin
     * @throws IOException Nese nuk ka internet per te marre te dhenat
     */

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

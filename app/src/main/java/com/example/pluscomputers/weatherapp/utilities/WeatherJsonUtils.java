package com.example.pluscomputers.weatherapp.utilities;

import com.example.pluscomputers.weatherapp.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherJsonUtils {

    public static List<Weather> extractMovies(String responseJson) throws JSONException {

        List<Weather> movies = new ArrayList<>();

        JSONObject responseObj = new JSONObject(responseJson);

            JSONArray movieArray = responseObj.getJSONArray("results");

            for (int i = 0; i < movieArray.length(); i++) {
                JSONObject movieObj = movieArray.getJSONObject(i);

                Weather movie = extractMovie(movieObj);

                movies.add(movie);
        }

        return movies;
    }

    public static Weather extractMovie(JSONObject movieObj) throws JSONException {

        long id = movieObj.getLong("id");
        String title = movieObj.getString("title");
        String originalTitle = movieObj.getString("original_title");
        String overview = movieObj.getString("overview");
        String posterPath = movieObj.getString("poster_path");
        double rating = movieObj.getInt("vote_average");
        String releaseDate = movieObj.getString("release_date");

        Weather movie = new Weather();

        return movie;
    }
}

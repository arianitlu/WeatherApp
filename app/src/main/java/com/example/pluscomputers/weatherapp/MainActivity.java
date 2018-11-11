package com.example.pluscomputers.weatherapp;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pluscomputers.weatherapp.adapters.WeatherAdapter;
import com.example.pluscomputers.weatherapp.model.Days;
import com.example.pluscomputers.weatherapp.model.Weather;
import com.example.pluscomputers.weatherapp.utilities.GPStracker;
import com.example.pluscomputers.weatherapp.utilities.NetworkUtils;
import com.example.pluscomputers.weatherapp.utilities.WeatherJsonUtils;
import com.example.pluscomputers.weatherapp.utilities.WeatherUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private double latitudeGps;
    private double longitudeGps;

    private RecyclerView recyclerView;
    WeatherAdapter adapter = new WeatherAdapter(this);
    Weather weather ;

    TextView dateTxt,highTemp,lowTemp;
    ImageView weatherIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (weather == null){
            new weatherApi().execute("Prizren");
        }

    }

    private class weatherApi extends AsyncTask<String, Void, String> {

        String response = "";

        @Override
        protected String doInBackground(String... strings) {
            String url1 = strings[0];

            URL url = NetworkUtils.buildWeatherCityUrl(url1);

            try {
                response = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {

                weather = WeatherJsonUtils.getWeather(result);

                populateTodayWeather();

                adapter.setWeatherList(weather.getHourlyList());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void populateTodayWeather(){
        dateTxt = findViewById(R.id.date);
        highTemp = findViewById(R.id.high_temperature);
        lowTemp = findViewById(R.id.low_temperature);
        weatherIcon = findViewById(R.id.weather_icon);

        dateTxt.setText(WeatherUtils.formatDate(weather.getLocalTime()));
        highTemp.setText(weather.getTempC() + "\u00b0");
        lowTemp.setText(weather.getFeelsLikeC() + "\u00b0");
        Picasso.get()
                .load(weather.getWeatherIconUrl())
                .into(weatherIcon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.weather, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

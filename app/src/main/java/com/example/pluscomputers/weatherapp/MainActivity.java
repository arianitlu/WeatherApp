package com.example.pluscomputers.weatherapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener{

    private double latitudeGps;
    private double longitudeGps;

    private RecyclerView recyclerView;
    WeatherAdapter adapter = new WeatherAdapter(this);
    Weather weather ;

    TextView dateTxt,highTemp,lowTemp;
    ImageView weatherIcon;

    private String unitTemperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definimi dhe implementimi i RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Thirrja metodes sharedPreferenes ku ne baze te Settings do te ndryshohen karakteristika te caktuara ne app
        setupSharedPreferences();

//        if (weather == null){
//            new weatherApi().execute("Prizren");
//        }

    }

    /**
     * Metoda asinkrone per thirrjen e nje URL dhe marrjen e te dhenave prej formatit JSON ne formen
     * qe deshirojme.
     */
    private class weatherApi extends AsyncTask<String, Void, String> {

        String response = "";

        @Override
        protected String doInBackground(String... strings) {
            String url1 = strings[0];

            // Krijimi i URL nga parametri hyres (ne rastin tone qyteti)
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

                // Pas parsimit te JSON na kthehet nje objekt i modelit(klases) tone Weather
                weather = WeatherJsonUtils.getWeather(result);

                // Popullohet me te dhena te motit dita e sotme (view kryesor)
                populateTodayWeather();

                // Adapterit i dergohet lista e diteve me te dhena ne menyre qe te shfaq ne
                // listen tone te avancuar
                adapter.setWeatherList(weather.getHourlyList());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Metoda per mbushjen me te dhenat e motit te sotem
    public void populateTodayWeather(){
        dateTxt = findViewById(R.id.date);
        highTemp = findViewById(R.id.high_temperature);
        lowTemp = findViewById(R.id.low_temperature);
        weatherIcon = findViewById(R.id.weather_icon);

        // Shfrytezimi i metodes ndihmese per formatin e deshiruar te DATE-s
        dateTxt.setText(WeatherUtils.formatDate(weather.getLocalTime()));


            highTemp.setText(weather.getTempC() + "\u00b0");
            lowTemp.setText(weather.getFeelsLikeC() + "\u00b0");

        Picasso.get()
                .load(weather.getWeatherIconUrl())
                .into(weatherIcon);
    }


    // Krijimi i menu-s per settings
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.weather, menu);
        return true;
    }

    /**
     *
     * @param item Nje item i menu
     * @return Na kthehet SettingsActivity pas klikimit
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * @param unit Si parameter hyres eshte njesia matese temperatures (celsius ose fahrenheit)
     *             Kjo metode do te shfrytezohet nga sharedPreferences perkatesisht nga
     *             SettingsFragment qe ne baze te ndryshimit te njesise matese ne aplikacion
     *             te ndryshohen te dhenat
     */
    public void populateTodayWeather(String unit){
        dateTxt = findViewById(R.id.date);
        highTemp = findViewById(R.id.high_temperature);
        lowTemp = findViewById(R.id.low_temperature);
        weatherIcon = findViewById(R.id.weather_icon);

        dateTxt.setText(WeatherUtils.formatDate(weather.getLocalTime()));

        if (unit == "celsius") {
            highTemp.setText(weather.getTempC() + "\u00b0");
            lowTemp.setText(weather.getFeelsLikeC() + "\u00b0");
        }else{
            highTemp.setText(weather.getTempC() + "F");
            lowTemp.setText(weather.getFeelsLikeC() + "F");
        }
        Picasso.get()
                .load(weather.getWeatherIconUrl())
                .into(weatherIcon);
    }

    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loadTemperatureBySettings(sharedPreferences);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    // Metode e cila do te ndryshoje njesine matese te temperatures
    private void loadTemperatureBySettings(SharedPreferences sharedPreferences) {
        unitTemperature = sharedPreferences.getString(getString(R.string.pref_location_key)
                    ,getString(R.string.pref_location_default));

        Toast.makeText(this, unitTemperature.toString(), Toast.LENGTH_SHORT).show();

        new weatherApi().execute("Prizren");
    }

    // Implementimi i metodes onSharedPreferenceChanged ku do te shikohet nese ka ndonje ndryshim ne settings
    // dhe menjehere ai ndryshim do te reflektohet ne program
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_location_key))){
            loadTemperatureBySettings(sharedPreferences);
        }
    }

}

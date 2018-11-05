package com.example.pluscomputers.weatherapp;

import android.Manifest;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pluscomputers.weatherapp.utilities.GPStracker;

public class MainActivity extends AppCompatActivity {

    private double latitudeGps;
    private double longitudeGps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // MyWeatherAPI

    }

    public void callGPS() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        GPStracker g = new GPStracker(getApplication());
        Location l = g.getLocation();

        if (l != null) {
            latitudeGps = l.getLatitude();
            longitudeGps = l.getLongitude();
        }

    }

}

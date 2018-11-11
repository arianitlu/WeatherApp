package com.example.pluscomputers.weatherapp;

import android.Manifest;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pluscomputers.weatherapp.adapters.WeatherAdapter;
import com.example.pluscomputers.weatherapp.model.Days;
import com.example.pluscomputers.weatherapp.utilities.GPStracker;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private double latitudeGps;
    private double longitudeGps;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        WeatherAdapter adapter = new WeatherAdapter(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setLajmi(listOfDays());
    }

    private List<Days> listOfDays() {
        List<Days> list = new ArrayList<>();

        list.add(new Days("Sunday , 2018","24\u00b0","19\u00b0",
                "-","Rainy", "-","-","-"));
        list.add(new Days("Monday , 2018","30\u00b0","20\u00b0",
                "-","Sunny", "-","-","-"));
        list.add(new Days("Tuesday , 2018","23\u00b0","11\u00b0",
                "-","Foggy", "-","-","-"));
        list.add(new Days("Wednesday , 2018","27\u00b0","23\u00b0",
                "-","Sunny", "-","-","-"));

        return list;
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

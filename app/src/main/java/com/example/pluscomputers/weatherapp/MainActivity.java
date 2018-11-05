package com.example.pluscomputers.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // MyWeatherAPI

        //http://api.worldweatheronline.com/premium/v1/weather.ashx?key=ec6a2189bd7e486588b133648182410
        // &q=Prizren&format=json&num_of_days=5&tp=12&showlocaltime=yes
    }
}

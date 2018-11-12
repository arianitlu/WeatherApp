package com.example.pluscomputers.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pluscomputers.weatherapp.utilities.WeatherUtils;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {

    TextView dateTxt,maxTempCtxt,minTempCtxt,weatherDescTxt,humidityTxt,pressureTxt,chanceOfRaintxt;
    ImageView iconUrlImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dateTxt = findViewById(R.id.date);
        maxTempCtxt = findViewById(R.id.high_temperature);
        minTempCtxt = findViewById(R.id.low_temperature);
        weatherDescTxt = findViewById(R.id.weather_description);
        humidityTxt = findViewById(R.id.humidity);
        pressureTxt = findViewById(R.id.pressure);
        chanceOfRaintxt = findViewById(R.id.wind_measurement);
        iconUrlImg = findViewById(R.id.weather_icon);

        Intent intent = getIntent();

        String date = intent.getStringExtra("date");
        String iconUrl = intent.getStringExtra("icon");
        String maxTempC = intent.getStringExtra("maxTempC");
        String minTempC = intent.getStringExtra("minTempC");
        String weatherDesc = intent.getStringExtra("weatherDesc");
        String humidity = intent.getStringExtra("humidity");
        String pressure = intent.getStringExtra("pressure");
        String chanceOfRain = intent.getStringExtra("chanceOfRain");

        dateTxt.setText(WeatherUtils.formatDateForAdapter(date));
        Picasso.get()
                .load(iconUrl)
                .into(iconUrlImg);
        maxTempCtxt.setText(maxTempC + "\u00b0");
        minTempCtxt.setText(minTempC + "\u00b0");
        weatherDescTxt.setText(weatherDesc);
        humidityTxt.setText(humidity + "%");
        pressureTxt.setText(pressure + " hPA");
        chanceOfRaintxt.setText(chanceOfRain + "%");

    }
}

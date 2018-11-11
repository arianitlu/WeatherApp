package com.example.pluscomputers.weatherapp.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.support.v4.app.ActivityCompat;

import com.example.pluscomputers.weatherapp.MainActivity;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherUtils {

    public static String formatDate(String apiDate){
        Date date = stringToDate(apiDate,"yyyy-MM-dd hh:mm");

//        return DateFormat.getDateInstance().format(date);

        DateFormat format2=new SimpleDateFormat("EEEE");
        String finalDay=format2.format(date);

        return finalDay;
    }

    public static String formatDateForAdapter(String apiDate){
        Date date = stringToDate(apiDate,"yyyy-MM-dd");

        DateFormat format2=new SimpleDateFormat("EEEE");
        String finalDay=format2.format(date);

        return finalDay;
    }

    public static Date stringToDate(String aDate, String aFormat) {

        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }

    public static Double[] getCoordinates(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        GPStracker g = new GPStracker(activity);
        Location l = g.getLocation();

        Double[] coordinates = new Double[2];

        if (l != null) {
            coordinates[0] = l.getLatitude();
            coordinates[1] = l.getLongitude();
        }

        return coordinates;

    }
}

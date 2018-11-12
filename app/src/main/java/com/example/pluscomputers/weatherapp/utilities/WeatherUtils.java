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

    /**
     *
     * @param apiDate Si parameter i dergojme daten e marrur nga API e cila eshte e formatit
     *                yyyy-MM-dd hh:mm dhe deshirojme te shenderrojme ne formati qe na kthen vetem diten
     * @return Na kthehet formati i deshiruar (vetem dita e javes)
     */

    public static String formatDate(String apiDate){
        Date date = stringToDate(apiDate,"yyyy-MM-dd hh:mm");

        DateFormat format2=new SimpleDateFormat("EEEE");
        String finalDay=format2.format(date);

        return finalDay;
    }

    /**
     *
     * @param apiDate Edhe ketu merret data nga API e cila tash eshte nje formati pak me ndryshe yyyy-MM-dd
     * @return Kthehet emri i dits e javes
     */

    public static String formatDateForAdapter(String apiDate){
        Date date = stringToDate(apiDate,"yyyy-MM-dd");

        DateFormat format2=new SimpleDateFormat("EEEE");
        String finalDay=format2.format(date);

        return finalDay;
    }

    /**
     *
     * @param aDate Data nga API me formatin e caktuar
     * @param aFormat Formati i caktuar i dates
     * @return Kthehet data ne formatin DATE
     */

    public static Date stringToDate(String aDate, String aFormat) {

        if(aDate==null) return null;
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }

    /**
     *
     * @param activity Pasohet aktiviteti pasi kjo metode per thirrjen e GPS duhet ta kete parameter aktivitetin
     *                 se nga ku po thirret.
     * @return         Pas thirrjes se kesaj metode na kthehet nje array i tipit Double ku aty jane
     *                 latitude dhe longitude e lokacionit tone
     */

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

    /**
     *
     * @param celsius Merr si parameter nje vlere te tipit double(celsius)
     * @return Na kthen vleren ne fahrenheit
     */

    public static double celsiusToFahrenheit(double celsius) {
        double fahrenheit = (5.0 / 9) * celsius + 32;

        return fahrenheit;
    }

    /**
     *
     * @param fahrenheit Merr si parameter nje vlere te tipit double (fahrenheit)
     * @return  Na kthen vleren ne celsius
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        double celsius = (9.0 / 5) * fahrenheit - 32;

        return celsius;

    }
}

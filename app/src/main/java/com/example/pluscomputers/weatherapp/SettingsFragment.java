package com.example.pluscomputers.weatherapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Nje preference gjenerale e marrum nga resourset
        addPreferencesFromResource(R.xml.pref_general);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();

        PreferenceScreen prefScreen = getPreferenceScreen();

        int count = prefScreen.getPreferenceCount();

        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);
            if (!(p instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }
        }
    }

    /**
     *
     * @param preference Preferenca e caktuar , nje pjese e settings
     * @param value Vlera qe eshte selektuar dhe ajo vlere shfaqet ne tekstin me poshte
     */
    private void setPreferenceSummary(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            preference.setSummary(stringValue);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // unregister the preference change listener
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // register the preference change listener
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    // Implementimi i interface onSharedPreferenceChanged , kur ndryshon ndonje settings te reflektohet
    // menjehere me ndryshimin e tekstit meposhte qe tregon karakteristiken aktuale
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //Activity activity = getActivity();

//        if (key.equals(getString(R.string.pref_location_key))) {
//            // we've changed the location
//            // Wipe out any potential PlacePicker latlng values so that we can use this text entry.
//            //SunshinePreferences.resetLocationCoordinates(activity);
//            //SunshineSyncUtils.startImmediateSync(activity);
//        } else if (key.equals(getString(R.string.pref_units_key))) {
//            // units have changed. update lists of weather entries accordingly
//            //activity.getContentResolver().notifyChange(WeatherContract.WeatherEntry.CONTENT_URI, null);
//        }
        Preference preference = findPreference(key);
        if (null != preference) {
            if (!(preference instanceof CheckBoxPreference)) {
                setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
            }
        }
    }
}

package com.dnet.myapplication.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private static SharedPreference yourPreference;
    private SharedPreferences sharedPreferences;

    public static SharedPreference getInstance(Context context) {
        if (yourPreference == null) {
            yourPreference = new SharedPreference(context);
        }
        return yourPreference;
    }

    private SharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

    public void saveData(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor .putString(key, value);
        prefsEditor.commit();
    }

    public String getData(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }
}

package com.example.saveprac;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private SharedPreferences sharedPref;

    public PreferenceManager(Context context) {
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setLoggedIn(boolean isLoggedIn) {
        sharedPref.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }

    public boolean isLoggedIn() {
        return sharedPref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void saveUsername(String name) {
        sharedPref.edit().putString(KEY_USERNAME, name).apply();
    }

    public String getUsername() {
        return sharedPref.getString(KEY_USERNAME, "User");
    }


    public void savePassword(String password) {
        sharedPref.edit().putString(KEY_PASSWORD, password).apply();
    }


    public String getPassword() {
        return sharedPref.getString(KEY_PASSWORD, null);
    }


    public void clearData() {
        sharedPref.edit().clear().apply();
    }
}



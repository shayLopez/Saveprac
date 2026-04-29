package com.example.saveprac;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_IS_REGISTERED = "isRegistered";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_AUTOLOG="autolog";

    private SharedPreferences sharedPref;

    public PreferenceManager(Context context) {
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // ---------- REGISTERED ----------
    public void setRegistered(boolean isRegistered) {
        sharedPref.edit().putBoolean(KEY_IS_REGISTERED, isRegistered).apply();
    }

    public boolean isRegistered() {
        return sharedPref.getBoolean(KEY_IS_REGISTERED, false);
    }

    public void setAutolog(boolean autolog)
    {
        sharedPref.edit().putBoolean(KEY_AUTOLOG,autolog).apply();
    }
    public boolean isAutoLog(){return sharedPref.getBoolean(KEY_AUTOLOG,false);}

    // ---------- USERNAME ----------
    public void saveUsername(String name) {
        sharedPref.edit().putString(KEY_USERNAME, name).apply();
    }

    public String getUsername() {
        return sharedPref.getString(KEY_USERNAME, "User");
    }

    // ---------- PASSWORD ----------
    public void savePassword(String password) {
        sharedPref.edit().putString(KEY_PASSWORD, password).apply();
    }

    public String getPassword() {
        return sharedPref.getString(KEY_PASSWORD, null);
    }


    public void saveEmail(String email) {
        sharedPref.edit().putString(KEY_EMAIL, email).apply();
    }

    public String getEmail() {
        return sharedPref.getString(KEY_EMAIL, null);
    }


    public void clearData() {
        sharedPref.edit().clear().apply();
    }
}

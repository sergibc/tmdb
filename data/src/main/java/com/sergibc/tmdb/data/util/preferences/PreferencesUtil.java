package com.sergibc.tmdb.data.util.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Util class to perform operations in a shared preferences file
 */
@Singleton
public class PreferencesUtil {

    private static final String API_KEY = "API_KEY";

    private SharedPreferences sharedPreferences;

    @Inject
    public PreferencesUtil(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Look for the value of a key in the shared preferences file
     *
     * @param key          String name of the property
     * @param defaultValue default value
     *
     * @return String value of the property. By default an empty String will be returned.
     */
    private String getStringProperty(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * Save a String property in the shared preferences file
     *
     * @param key   String name of the property
     * @param value String value of the property
     */
    private void setStringProperty(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Get the value of the API_KEY
     */
    public String getApiKey() {
        return getStringProperty(API_KEY, null);
    }

    /**
     * Save the value of the API_KEY
     *
     * @param apiKey String value of the API_KEY
     */
    public void setApiKey(String apiKey) {
        setStringProperty(API_KEY, apiKey);
    }
}

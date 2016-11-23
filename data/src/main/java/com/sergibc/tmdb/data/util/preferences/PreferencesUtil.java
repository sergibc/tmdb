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

    private SharedPreferences sharedPreferences;

    @Inject
    public PreferencesUtil(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    // TODO Add all preferences needed
}

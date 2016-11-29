package com.sergibc.tmdb.utils;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Util methods for screen operations
 */
public class ScreenUtils {

    /**
     * Returns true if is a tablet screen
     */
    public static boolean isTablet(Context context) {
        return context != null
                && (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}

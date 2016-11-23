package com.sergibc.tmdb.data.util;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.Locale;

/**
 * Util class with information retrieved from the device
 */
public final class DeviceUtil {

    private DeviceUtil() {
    }

    /**
     * Get device id.
     *
     * @param context Context application.
     *
     * @return String device id.
     */
    public static String getDeviceSerialNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static String getDeviceUniqueId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * Get the device language. We support 'es', 'en', 'de', 'it', 'fr', 'ja'. If it is not one them, then language will be 'en'
     */
    public static String getDeviceLanguage() {
        String deviceLanguage = Locale.getDefault().getLanguage();
        if ("en".equals(deviceLanguage)
                || "es".equals(deviceLanguage)
                || "it".equals(deviceLanguage)
                || "de".equals(deviceLanguage)
                || "fr".equals(deviceLanguage)
                || "ja".equals(deviceLanguage)) {
            return deviceLanguage;
        } else {
            return "en";
        }
    }

}
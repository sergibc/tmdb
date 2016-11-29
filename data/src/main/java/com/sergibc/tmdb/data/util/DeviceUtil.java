package com.sergibc.tmdb.data.util;

import java.util.Locale;

/**
 * Util class with information retrieved from the device
 */
public final class DeviceUtil {

    private DeviceUtil() {
    }

    /**
     * Get the device language. We support 'es' and 'en', but we can add any language supported by the Api
     */
    public static String getDeviceLanguage() {
        String deviceLanguage = Locale.getDefault().getLanguage();
        if ("en".equals(deviceLanguage) || "es".equals(deviceLanguage)) {
            return deviceLanguage;
        } else {
            return "en";
        }
    }

}
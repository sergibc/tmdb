package com.sergibc.tmdb.data.util;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Util class to work with dates
 */
public final class DateUtils {

    private static final String TAG = "DateUtils";

    private static final String DATE_PATTERN = "yyyy-MM-dd"; // Date pattern of the service's responses

    private static final String DATE_PATTERN_COMPLETE = "yyyy-MM-dd'T'HH:mm:ss"; // Date pattern of the service's responses

    private DateUtils() {
    }

    private static Date getDateFromString(String dateString, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(dateString);
    }

    public static String getYear(String textDate) {
        if (textDate != null && !TextUtils.isEmpty(textDate)) {
            Date date = null;
            try {
                date = getDateFromString(textDate, DATE_PATTERN);
            } catch (ParseException e) {
                try {
                    date = getDateFromString(textDate, DATE_PATTERN_COMPLETE);
                } catch (ParseException e1) {
                    Log.e(TAG, "getYear: ", e1);
                }
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return String.valueOf(calendar.get(Calendar.YEAR));
        } else {
            return "";
        }
    }

}
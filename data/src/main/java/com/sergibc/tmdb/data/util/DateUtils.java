package com.sergibc.tmdb.data.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Util class to work with dates
 */
public final class DateUtils {

    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"; // Date pattern of the service's responses

    private DateUtils() {
    }

    public static String getCurrentTime(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(new Date());
    }

    public static String getCurrentTime() {
        return getCurrentTime(DATE_PATTERN);
    }

    private static Date getDateFromString(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return convertedDate;
    }

    public static String parseDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(date);
    }

    public static String parseDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
        return sdf.format(date);
    }

    public static String getYear(String textDate) {
        Date date = getDateFromString(textDate, DATE_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

}
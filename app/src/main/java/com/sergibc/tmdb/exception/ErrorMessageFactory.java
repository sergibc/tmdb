package com.sergibc.tmdb.exception;


import com.sergibc.tmdb.data.exception.NetworkConnectionException;

import android.content.Context;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public final class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }

    /**
     * Creates a String representing an error message.
     *
     * @param context   Context needed to retrieve string resources.
     * @param exception An exception used as a condition to retrieve the correct error message.
     *
     * @return {@link String} an error message.
     */
    public static String create(Context context, Exception exception) {
        // TODO Add all the exceptions needed with is string id
        String message = "";
        //        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException) {
            //            message = context.getString(R.string.exception_message_no_connection);
        }

        return message;
    }
}
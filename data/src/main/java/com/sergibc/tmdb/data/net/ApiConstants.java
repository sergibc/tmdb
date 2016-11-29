package com.sergibc.tmdb.data.net;

import com.sergibc.tmdb.data.BuildConfig;

/**
 * API Constants
 */
public interface ApiConstants {

    String API_KEY = BuildConfig.API_KEY; // Should be in a properties file and not pushed to the VCS

    String API_VERSION = "3";

    String ENDPOINT = "https://api.themoviedb.org/" + API_VERSION + "/";

    String IMAGES_SERVICE = "http://image.tmdb.org/t/p/w500";

}
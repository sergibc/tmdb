package com.sergibc.tmdb.data.net;

/**
 * API Constants
 */
public interface ApiConstants {

    String API_KEY = "93aea0c77bc168d8bbce3918cefefa45"; // Should be in a properties file and not pushed to the VCS

    String API_VERSION = "3";

    String ENDPOINT = "https://api.themoviedb.org/" + API_VERSION + "/";

    String IMAGES_SERVICE = "http://image.tmdb.org/t/p/w500";

}
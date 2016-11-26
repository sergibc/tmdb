package com.sergibc.tmdb.model;

import lombok.Data;

/**
 * Model of a movie item for presentation layer
 */
@Data
public class MovieItemViewModel {

    private String imagePath;

    private String overview;

    private String year;

    private int id;

    private String title;

    //        private String backdropPath;

}

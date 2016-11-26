package com.sergibc.tmdb.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Model of movies for presentation layer
 */
@Data
public class MovieViewModel {
    private int page;

    private List<MovieItemViewModel> movies = new ArrayList<>();

    private int totalResults;

    private int totalPages;

}

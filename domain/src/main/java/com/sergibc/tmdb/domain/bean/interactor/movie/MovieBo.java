package com.sergibc.tmdb.domain.bean.interactor.movie;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Business object for movies
 */
@Data
public class MovieBo {

    private String posterPath;

    private boolean adult;

    private String overview;

    private String releaseDate;

    private List<Integer> genreIds = new ArrayList<>();

    private int id;

    private String originalTitle;

    private String originalLanguage;

    private String title;

    private String backdropPath;

    private double popularity;

    private int voteCount;

    private boolean video;

    private double voteAverage;
}

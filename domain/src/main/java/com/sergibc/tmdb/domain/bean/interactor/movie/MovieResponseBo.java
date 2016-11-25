package com.sergibc.tmdb.domain.bean.interactor.movie;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Response BO for movies
 */
@Data
public class MovieResponseBo {

    private int page;

    private List<MovieBo> movies = new ArrayList<>();

    private int totalResults;

    private int totalPages;
}

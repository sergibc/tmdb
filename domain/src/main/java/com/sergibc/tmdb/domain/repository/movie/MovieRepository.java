package com.sergibc.tmdb.domain.repository.movie;

import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;

import rx.Observable;

/**
 * Repository for movies
 */
public interface MovieRepository {

    Observable<MovieResponseBo> getPopularMovies(int page, boolean refresh);

    Observable<MovieResponseBo> searchMovies(int page, String query, boolean refresh);
}

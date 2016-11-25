package com.sergibc.tmdb.data.repository.movie.datasource;

import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;

import rx.Observable;

/**
 * Movie data source
 */

public interface MovieDataStore {

    Observable<MovieResponseBo> getPopularMovies(int page);

    // TODO We can change 'query' by a 'MovieQuery' object or something like that
    Observable<MovieResponseBo> searchMovies(int page, String query);
}

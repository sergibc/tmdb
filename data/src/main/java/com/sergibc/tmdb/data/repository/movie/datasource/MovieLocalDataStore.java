package com.sergibc.tmdb.data.repository.movie.datasource;

import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;

import android.content.Context;

import rx.Observable;

/**
 * Local data store for movies
 */

public class MovieLocalDataStore implements MovieDataStore {

    private final Context context;

    public MovieLocalDataStore(Context context) {
        this.context = context;
    }

    @Override
    public Observable<MovieResponseBo> getPopularMovies(int page) {
        return null;
    }

    @Override
    public Observable<MovieResponseBo> searchMovies(int page, String query) {
        return null;
    }
}

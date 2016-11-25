package com.sergibc.tmdb.data.repository.movie.datasource;


import com.sergibc.tmdb.data.mapper.dto.movie.MovieMapper;
import com.sergibc.tmdb.data.net.Api;
import com.sergibc.tmdb.data.repository.datasource.CommonCloudDataStore;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;

import android.content.Context;

import java.util.Map;

import rx.Observable;

/**
 * Cloud data store for Movie
 */
public class MovieCloudDataStore extends CommonCloudDataStore implements MovieDataStore {

    private final Api api;

    public MovieCloudDataStore(Context context) {
        super(context);

        api = buildRetrofit().create(Api.class);
    }

    @Override
    protected Map<String, String> getHeaders() {
        // This service doesn't need headers
        return null;
    }

    @Override
    public Observable<MovieResponseBo> getPopularMovies(int page) {
        return api.getPopularMovies(getApiKey(), getLanguage(), page)
                .map(movieResponse -> new MovieMapper().modelToData(movieResponse));
    }

    @Override
    public Observable<MovieResponseBo> searchMovies(int page, String query) {
        return api.searchMovies(getApiKey(), getLanguage(), query, page)
                .map(movieResponse -> new MovieMapper().modelToData(movieResponse));
    }
}

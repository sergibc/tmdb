package com.sergibc.tmdb.data.repository.movie;

import com.sergibc.tmdb.data.repository.movie.datasource.MovieDataStoreFactory;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;
import com.sergibc.tmdb.domain.repository.movie.MovieRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Implementation of the repository for movies
 */
public class MovieRepositoryImpl implements MovieRepository {

    private MovieDataStoreFactory movieDataStoreFactory;

    @Inject
    public MovieRepositoryImpl(MovieDataStoreFactory movieDataStoreFactory) {
        this.movieDataStoreFactory = movieDataStoreFactory;
    }

    @Override
    public Observable<MovieResponseBo> getPopularMovies(int page, boolean refresh) {
        return movieDataStoreFactory.create(refresh).getPopularMovies(page);
    }

    @Override
    public Observable<MovieResponseBo> searchMovies(int page, String query, boolean refresh) {
        return movieDataStoreFactory.create(refresh).searchMovies(page, query);
    }
}

package com.sergibc.tmdb.internal.di.module;

import com.sergibc.tmdb.domain.executor.PostExecutionThread;
import com.sergibc.tmdb.domain.executor.ThreadExecutor;
import com.sergibc.tmdb.domain.interactor.Interactor;
import com.sergibc.tmdb.domain.interactor.movie.GetPopularMoviesUseCase;
import com.sergibc.tmdb.domain.interactor.movie.SearchMoviesUseCase;
import com.sergibc.tmdb.domain.repository.movie.MovieRepository;
import com.sergibc.tmdb.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for movies
 */
@Module
public class MovieModule {

    @Provides
    @PerActivity
    @Named("getPopularMoviesUseCase")
    Interactor provideGetPopularMoviesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
            MovieRepository movieRepository) {
        return new GetPopularMoviesUseCase(threadExecutor, postExecutionThread, movieRepository);
    }

    @Provides
    @PerActivity
    @Named("searchMoviesUseCase")
    Interactor provideSearchMoviesUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
            MovieRepository movieRepository) {
        return new SearchMoviesUseCase(threadExecutor, postExecutionThread, movieRepository);
    }

}

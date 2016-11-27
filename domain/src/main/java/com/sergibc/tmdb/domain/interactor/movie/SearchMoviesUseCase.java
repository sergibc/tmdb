package com.sergibc.tmdb.domain.interactor.movie;

import com.sergibc.tmdb.domain.executor.PostExecutionThread;
import com.sergibc.tmdb.domain.executor.ThreadExecutor;
import com.sergibc.tmdb.domain.interactor.Interactor;
import com.sergibc.tmdb.domain.repository.movie.MovieRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Use case to get popular movies
 */
public class SearchMoviesUseCase extends Interactor {

    private final MovieRepository movieRepository;

    private int page;

    private String query;

    @Inject
    public SearchMoviesUseCase(ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            MovieRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    public void execute(int page, String query, Subscriber useCaseSubscriber) {
        this.page = page;
        this.query = query;
        super.execute(useCaseSubscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return movieRepository.searchMovies(page, query, true); // TODO manage the refresh value
    }
}

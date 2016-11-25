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
public class GetPopularMoviesUseCase extends Interactor {

    private final MovieRepository movieRepository;

    private int page;

    private boolean refresh;

    @Inject
    public GetPopularMoviesUseCase(ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            MovieRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    public void execute(int page, boolean refresh, Subscriber useCaseSubscriber) {
        this.page = page;
        this.refresh = refresh;
        super.execute(useCaseSubscriber);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return movieRepository.getPopularMovies(page, refresh);
    }
}

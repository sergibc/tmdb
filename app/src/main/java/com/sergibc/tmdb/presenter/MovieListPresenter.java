package com.sergibc.tmdb.presenter;

import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;
import com.sergibc.tmdb.domain.interactor.Interactor;
import com.sergibc.tmdb.domain.interactor.movie.GetPopularMoviesUseCase;
import com.sergibc.tmdb.domain.interactor.subscriber.DefaultSubscriber;
import com.sergibc.tmdb.view.IMovieListView;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Presenter for movie list fragment
 */
public class MovieListPresenter extends Presenter<IMovieListView> {

    private static final String TAG = "MovieListPresenter";

    private static final int DEFAULT_FIRST_PAGE = 1;

    private final Interactor getPopularMoviesUseCase;

    private final Interactor searchMoviesUseCase;

    private int page = DEFAULT_FIRST_PAGE;

    @Inject
    public MovieListPresenter(@Named("getPopularMoviesUseCase") Interactor getPopularMoviesUseCase,
            @Named("searchMoviesUseCase") Interactor searchMoviesUseCase) {
        this.getPopularMoviesUseCase = getPopularMoviesUseCase;
        this.searchMoviesUseCase = searchMoviesUseCase;
    }

    @Override
    public void initialize() {
        page = DEFAULT_FIRST_PAGE;
        getPopularMovies();
    }

    @Override
    public void resume() {
        //        subscribeToBus();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void subscribeToBus() {
        //        subscriptions = new CompositeSubscription();
        //        subscriptions.add(rxBus.toObservable().subscribe(new Action1<Object>() {
        //            @Override
        //            public void call(Object o) {
        //                Log.d(TAG, "subscribeToBus - Event received");
        //            }
        //        }));
    }

    private void getPopularMovies() {
        ((GetPopularMoviesUseCase) getPopularMoviesUseCase).execute(page, true, new DefaultSubscriber<MovieResponseBo>() {

            private MovieResponseBo responseBo;

            @Override
            public void onCompleted() {
                super.onCompleted();
                if (responseBo != null) {
                    Log.d(TAG, "getPopularMovies: " + responseBo);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "getPopularMovies: " + e);
                super.onError(e);
            }

            @Override
            public void onNext(MovieResponseBo movieResponseBo) {
                super.onNext(movieResponseBo);
                this.responseBo = movieResponseBo;
            }
        });
    }
}

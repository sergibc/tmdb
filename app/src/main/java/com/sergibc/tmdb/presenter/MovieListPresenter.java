package com.sergibc.tmdb.presenter;

import com.sergibc.tmdb.data.util.NetworkUtils;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;
import com.sergibc.tmdb.domain.interactor.Interactor;
import com.sergibc.tmdb.domain.interactor.movie.GetPopularMoviesUseCase;
import com.sergibc.tmdb.domain.interactor.movie.SearchMoviesUseCase;
import com.sergibc.tmdb.domain.interactor.subscriber.DefaultSubscriber;
import com.sergibc.tmdb.view.IMovieListView;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Presenter for movie list fragment
 */
public class MovieListPresenter extends Presenter<IMovieListView> {

    public static final int DEFAULT_FIRST_PAGE = 1;

    private static final String TAG = "MovieListPresenter";

    private final Interactor getPopularMoviesUseCase;

    private final Interactor searchMoviesUseCase;

    private int page = DEFAULT_FIRST_PAGE;

    private int totalPages = DEFAULT_FIRST_PAGE;

    private String query;

    @Inject
    Context context;

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
        if (NetworkUtils.isNetworkAvailable(context)) {
            if (page <= totalPages) {
                view.showLoading();
                view.hideEmptyView();
                ((GetPopularMoviesUseCase) getPopularMoviesUseCase).execute(page, true, new DefaultSubscriber<MovieResponseBo>() {

                    private MovieResponseBo responseBo;

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        view.hideLoading();
                        if (responseBo != null) {
                            //                    Log.d(TAG, "getPopularMovies: " + responseBo);
                            totalPages = responseBo.getTotalPages();
                            view.addMoviesToList(responseBo);
                        } else {
                            view.showEmptyView();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "getPopularMovies: " + e); // TODO
                        super.onError(e);
                        view.hideLoading();
                        view.showError(true);
                    }

                    @Override
                    public void onNext(MovieResponseBo movieResponseBo) {
                        super.onNext(movieResponseBo);
                        this.responseBo = movieResponseBo;
                    }
                });
            } else {
                view.hideLoading();
            }
        } else {
            view.showNoConnection();
        }
    }

    private void searchMovies() {
        if (NetworkUtils.isNetworkAvailable(context)) {
            if (page <= totalPages) {
                view.showLoading();
                view.hideEmptyView();
                ((SearchMoviesUseCase) searchMoviesUseCase).execute(page, query, new DefaultSubscriber<MovieResponseBo>() {

                    MovieResponseBo responseBo;

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        view.hideLoading();
                        if (responseBo != null) {
                            totalPages = responseBo.getTotalPages();
                            view.addMoviesToList(responseBo);
                        } else {
                            view.showEmptyView();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "searchMovies: " + e);
                        super.onError(e);
                        view.hideLoading();
                        view.showError(false);
                    }

                    @Override
                    public void onNext(MovieResponseBo movieResponseBo) {
                        super.onNext(movieResponseBo);
                        this.responseBo = movieResponseBo;
                    }
                });
            } else {
                view.hideLoading();
            }
        } else {
            view.showNoConnection();
        }
    }

    public void search(int page, String query) {
        this.page = page;
        this.query = query;
        searchMovies();
    }

    public void loadPage(int page, boolean searching) {
        this.page = page;
        if (searching) {
            searchMovies();
        } else {
            getPopularMovies();
        }
    }
}

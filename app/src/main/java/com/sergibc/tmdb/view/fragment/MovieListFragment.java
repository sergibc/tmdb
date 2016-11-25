package com.sergibc.tmdb.view.fragment;

import com.sergibc.tmdb.R;
import com.sergibc.tmdb.internal.di.component.MovieComponent;
import com.sergibc.tmdb.presenter.MovieListPresenter;
import com.sergibc.tmdb.presenter.Presenter;
import com.sergibc.tmdb.view.IMovieListView;

import android.os.Bundle;

import javax.inject.Inject;

/**
 * Fragment for movie list
 */
public class MovieListFragment extends BaseFragment implements IMovieListView {

    @Inject
    MovieListPresenter presenter;

    public MovieListFragment() {

    }

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_movie_list;
    }

    @Override
    protected void initializeFragment(Bundle savedInstanceState) {
        initializeInjector();

        initializePresenter();
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    private void initializeInjector() {
        MovieComponent movieComponent = getComponent(MovieComponent.class);
        movieComponent.inject(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
        presenter.initialize();
    }
}

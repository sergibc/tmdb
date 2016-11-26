package com.sergibc.tmdb.view.fragment;

import com.sergibc.tmdb.R;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;
import com.sergibc.tmdb.internal.di.component.MovieComponent;
import com.sergibc.tmdb.model.MovieViewModel;
import com.sergibc.tmdb.model.mapper.ViewModelMovieMapper;
import com.sergibc.tmdb.presenter.MovieListPresenter;
import com.sergibc.tmdb.presenter.Presenter;
import com.sergibc.tmdb.view.IMovieListView;
import com.sergibc.tmdb.view.adapter.MoviesListAdapter;
import com.sergibc.tmdb.view.listener.EndlessScrollListener;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Fragment for movie list
 */
public class MovieListFragment extends BaseFragment implements IMovieListView {

    @Inject
    MovieListPresenter presenter;

    @BindView(R.id.movie_list)
    RecyclerView movieList;

    private MoviesListAdapter adapter;

    public MovieListFragment() {

    }

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    // TODO Review ButterKnife
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        //        unbinder = ButterKnife.bind(this, view);
        //        ButterKnife.setDebug(BuildConfig.DEBUG);
        movieList = (RecyclerView) view.findViewById(R.id.movie_list);

        return view;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_movie_list;
    }

    @Override
    protected void initializeFragment(Bundle savedInstanceState) {
        initializeInjector();

        initializeUI();

        initializePresenter();

        setListeners();
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

    private void initializeUI() {
        movieList.setLayoutManager(new LinearLayoutManager(getContext())); // TODO change
        adapter = new MoviesListAdapter(getContext());
        movieList.setAdapter(adapter);
    }

    private void setListeners() {
        movieList.addOnScrollListener(new EndlessScrollListener(movieList.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.loadPage(page);
            }
        });
    }

    @Override
    public void addMoviesToList(MovieResponseBo movieResponseBo) {
        MovieViewModel viewModel = new ViewModelMovieMapper().modelToData(movieResponseBo);

        if (viewModel != null) {
            adapter.setMovieViewModel(viewModel);
            adapter.addItems(viewModel.getMovies());
        }
    }
}

package com.sergibc.tmdb.view.fragment;

import com.sergibc.tmdb.R;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;
import com.sergibc.tmdb.internal.di.component.MovieComponent;
import com.sergibc.tmdb.model.MovieItemViewModel;
import com.sergibc.tmdb.model.MovieViewModel;
import com.sergibc.tmdb.model.mapper.ViewModelMovieMapper;
import com.sergibc.tmdb.presenter.MovieListPresenter;
import com.sergibc.tmdb.presenter.Presenter;
import com.sergibc.tmdb.view.IMovieListView;
import com.sergibc.tmdb.view.adapter.MoviesListAdapter;
import com.sergibc.tmdb.view.listener.EndlessScrollListener;
import com.sergibc.tmdb.view.widget.FabScrollBehavior;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Fragment for movie list
 */
public class MovieListFragment extends BaseFragment
        implements IMovieListView, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener, View.OnClickListener {

    private static final String TAG = "MovieListFragment";

    private static final String MOVIE_LIST_STATE = "state.movies";

    private static final String SEARCH_QUERY_STATE = "state.search_query";

    private static final int SEARCH_MINIMUM_CHARACTERS = 3;

    @Inject
    MovieListPresenter presenter;

    private RecyclerView movieList;

    private SearchView searchView;

    private TextView info;

    private View loadingView;

    private FloatingActionButton fab;

    private Toolbar toolbar;

    private MoviesListAdapter adapter;

    private boolean searching;

    private String savedQuery;

    public MovieListFragment() {

    }

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MOVIE_LIST_STATE, (ArrayList<? extends Parcelable>) adapter.getItems());
        if (searchView != null && searchView.getQuery() != null && !TextUtils.isEmpty(searchView.getQuery().toString())) {
            outState.putString(SEARCH_QUERY_STATE, searchView.getQuery().toString());
        }
    }

    // TODO Review ButterKnife
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);

        findViews(view);
        setActionBar();

        return view;
    }

    private void findViews(View view) {
        movieList = (RecyclerView) view.findViewById(R.id.movie_list);
        info = (TextView) view.findViewById(R.id.movie_info_results);
        loadingView = view.findViewById(R.id.movie_loading);
        fab = (FloatingActionButton) view.findViewById(R.id.movie_fab);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    }

    private void setActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_movie_list;
    }

    @Override
    protected void initializeFragment(Bundle savedInstanceState) {
        initializeInjector();

        setHasOptionsMenu(true);

        initializeUI();

        initializePresenter(savedInstanceState);

        restoreSavedState(savedInstanceState);

        setListeners();
    }

    private void restoreSavedState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            restoreMovies(savedInstanceState);
            restoreSearchQuery(savedInstanceState);
        }
    }

    private void restoreSearchQuery(Bundle savedInstanceState) {
        savedQuery = savedInstanceState.getString(SEARCH_QUERY_STATE);
    }

    private void restoreMovies(Bundle savedInstanceState) {
        List<MovieItemViewModel> movies = savedInstanceState.getParcelableArrayList(MOVIE_LIST_STATE);
        adapter.setItems(movies);
        if (movies == null || movies.isEmpty()) {
            showEmptyView();
        }
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    private void initializeInjector() {
        MovieComponent movieComponent = getComponent(MovieComponent.class);
        movieComponent.inject(this);
    }

    private void initializePresenter(Bundle savedInstanceState) {
        presenter.setView(this);
        if (savedInstanceState == null) {
            presenter.initialize();
        }
    }

    private void initializeUI() {
        movieList.setLayoutManager(new LinearLayoutManager(getContext())); // TODO change
        adapter = new MoviesListAdapter(getContext());
        movieList.setAdapter(adapter);
        setFabButtonVisibility();
    }

    private void setFabButtonVisibility() {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        layoutParams.setBehavior(new FabScrollBehavior());
        fab.setVisibility(View.GONE);
        fab.setLayoutParams(layoutParams);
    }

    private void setListeners() {
        movieList.addOnScrollListener(new EndlessScrollListener(movieList.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.loadPage(page, searching);
            }
        });

        fab.setOnClickListener(this);
    }

    @Override
    public void addMoviesToList(MovieResponseBo movieResponseBo) {
        MovieViewModel viewModel = new ViewModelMovieMapper().modelToData(movieResponseBo);

        if (viewModel != null) {
            adapter.setMovieViewModel(viewModel);
            adapter.addItems(viewModel.getMovies());
            if (viewModel.getMovies() != null && !viewModel.getMovies().isEmpty()) {
                hideEmptyView();
            } else {
                showEmptyView();
            }
        }
    }

    @Override
    public void hideEmptyView() {
        if (info != null) {
            info.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmptyView() {
        adapter.clearData();
        if (info != null) {
            info.setText(R.string.no_movies);
            info.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTypeThreeCharactersView() {
        if (info != null) {
            info.setText(R.string.type_three_characters);
            info.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideTypeThreeCharactersView() {
        if (info != null) {
            info.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movies, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(searchItem, this);

        View closeButton = searchView.findViewById(R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clearData();
                searchView.setQuery("", false);
            }
        });

        // restore search view after a screen orientation change
        if (savedQuery != null) {
            searchItem.expandActionView();
            searchView.setQuery(savedQuery, false);
            searchView.clearFocus();
        } else if (adapter.getItems() == null || adapter.getItems().isEmpty()) {
            initializePresenter(null);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d(TAG, "onQueryTextSubmit: " + query);
        search(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(TAG, "onQueryTextChange: " + newText);
        if (savedQuery == null
                || (!TextUtils.isEmpty(newText) && !newText.equals(savedQuery))) { // avoid search on screen orientation change
            search(newText);
        } else if (savedQuery != null && savedQuery.length() < SEARCH_MINIMUM_CHARACTERS) {
            showTypeThreeCharactersView();
        }
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        // Do nothing
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        adapter.clearData();
        initializePresenter(null);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.movie_fab:
                goTop();
                break;
            default:
                break;
        }
    }

    private void goTop() {
        fab.setVisibility(View.GONE);
        movieList.smoothScrollToPosition(0);
    }

    private void search(String query) {
        adapter.clearData();
        savedQuery = null;
        if (!TextUtils.isEmpty(query) && query.length() >= SEARCH_MINIMUM_CHARACTERS) {
            hideTypeThreeCharactersView();
            searching = true;
            presenter.search(MovieListPresenter.DEFAULT_FIRST_PAGE, query);
        } else {
            showTypeThreeCharactersView();
        }
    }
}

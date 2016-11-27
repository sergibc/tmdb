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
import com.sergibc.tmdb.view.widget.FabScrollBehavior;

import android.os.Bundle;
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

import javax.inject.Inject;

//import butterknife.BindView;

/**
 * Fragment for movie list
 */
public class MovieListFragment extends BaseFragment
        implements IMovieListView, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener, View.OnClickListener {

    private static final String TAG = "MovieListFragment";

    @Inject
    MovieListPresenter presenter;

    //    @BindView(R.id.movie_list)
    private RecyclerView movieList;

    private SearchView searchView;

    private View emptyView;

    private View loadingView;

    private FloatingActionButton fab;

    private Toolbar toolbar;

    private MoviesListAdapter adapter;

    private boolean searching;

    public MovieListFragment() {

    }

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    // TODO Review ButterKnife
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        //        unbinder = ButterKnife.bind(this, view);
        //        ButterKnife.setDebug(BuildConfig.DEBUG);
        movieList = (RecyclerView) view.findViewById(R.id.movie_list);
        emptyView = view.findViewById(R.id.movie_empty);
        loadingView = view.findViewById(R.id.movie_loading);
        fab = (FloatingActionButton) view.findViewById(R.id.movie_fab);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

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
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmptyView() {
        if (emptyView != null) {
            emptyView.setVisibility(View.VISIBLE);
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
        search(newText);
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
        initializePresenter();
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
        if (!TextUtils.isEmpty(query) && query.length() >= 3) {
            // TODO show loading
            searching = true;
            presenter.search(MovieListPresenter.DEFAULT_FIRST_PAGE, query);
        } else {
            // TODO show at least 3 chars
        }
    }
}

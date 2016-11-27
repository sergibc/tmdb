package com.sergibc.tmdb.view.activity;


import com.sergibc.tmdb.R;
import com.sergibc.tmdb.internal.di.HasComponent;
import com.sergibc.tmdb.internal.di.component.DaggerMovieComponent;
import com.sergibc.tmdb.internal.di.component.MovieComponent;
import com.sergibc.tmdb.presenter.Presenter;
import com.sergibc.tmdb.view.fragment.MovieListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

/**
 * Activity for movie list
 */
public class MovieListActivity extends BaseActivity implements HasComponent<MovieComponent> {

    private static final String TAG = "MovieListActivity";

    private static final int FRAGMENT_CONTAINER_ID = R.id.fragment_container;

    private MovieComponent movieComponent;

    public static Intent getCallingIntent(BaseActivity activity, Bundle extras) {
        Intent intent = new Intent(activity, MovieListActivity.class);
        if (extras != null) {
            intent.putExtras(extras);
        }
        return intent;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_movie_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_movie_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    @Override
    protected Presenter getPresenter() {
        return null;
    }

    private void init() {
        initializeActivity();
        initializeInjector();
    }

    private void initializeInjector() {
        movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

        movieComponent.inject(this);
    }

    @Override
    public MovieComponent getComponent() {
        return movieComponent;
    }

    private void initializeActivity() {
        Fragment fragment = MovieListFragment.newInstance();
        addFragment(FRAGMENT_CONTAINER_ID, fragment);
    }
}

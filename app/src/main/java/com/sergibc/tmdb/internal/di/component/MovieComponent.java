package com.sergibc.tmdb.internal.di.component;

import com.sergibc.tmdb.internal.di.PerActivity;
import com.sergibc.tmdb.internal.di.module.ActivityModule;
import com.sergibc.tmdb.internal.di.module.MovieModule;
import com.sergibc.tmdb.view.activity.MovieListActivity;
import com.sergibc.tmdb.view.fragment.MovieListFragment;

import dagger.Component;

/**
 * Dagger component for movies
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MovieModule.class})
public interface MovieComponent {

    void inject(MovieListActivity movieListActivity);

    void inject(MovieListFragment movieListFragment);
}

package com.sergibc.tmdb.internal.di.component;

import com.sergibc.tmdb.data.bus.RxBus;
import com.sergibc.tmdb.data.util.preferences.PreferencesUtil;
import com.sergibc.tmdb.domain.executor.PostExecutionThread;
import com.sergibc.tmdb.domain.executor.ThreadExecutor;
import com.sergibc.tmdb.domain.repository.movie.MovieRepository;
import com.sergibc.tmdb.internal.di.module.ApplicationModule;
import com.sergibc.tmdb.navigation.Navigator;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    // Add your inject() methods

    //Exposed to sub-graphs.
    Context context();

    PreferencesUtil preferencesUtil();

    RxBus rxBus();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    Navigator navigator();

    MovieRepository movieRepository();
}
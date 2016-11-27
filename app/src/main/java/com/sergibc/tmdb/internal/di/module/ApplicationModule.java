package com.sergibc.tmdb.internal.di.module;

import com.sergibc.tmdb.TMDBApplication;
import com.sergibc.tmdb.data.UIThread;
import com.sergibc.tmdb.data.bus.RxBus;
import com.sergibc.tmdb.data.executor.JobExecutor;
import com.sergibc.tmdb.data.repository.movie.MovieRepositoryImpl;
import com.sergibc.tmdb.domain.executor.PostExecutionThread;
import com.sergibc.tmdb.domain.executor.ThreadExecutor;
import com.sergibc.tmdb.domain.repository.movie.MovieRepository;
import com.sergibc.tmdb.navigation.Navigator;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final TMDBApplication application;

    public ApplicationModule(TMDBApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    RxBus provideRxBus() {
        return new RxBus();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    MovieRepository provideMovieRepository(MovieRepositoryImpl movieRepository) {
        return movieRepository;
    }
}
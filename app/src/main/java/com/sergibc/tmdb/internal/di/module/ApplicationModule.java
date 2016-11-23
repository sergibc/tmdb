package com.sergibc.tmdb.internal.di.module;

import com.sergibc.tmdb.TMDBApplication;
import com.sergibc.tmdb.data.bus.RxBus;

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
}
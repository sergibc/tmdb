package com.sergibc.tmdb;

import com.sergibc.tmdb.internal.di.component.ApplicationComponent;
import com.sergibc.tmdb.internal.di.component.DaggerApplicationComponent;
import com.sergibc.tmdb.internal.di.module.ApplicationModule;

import android.app.Application;

/**
 * Android Main Application
 */
public class TMDBApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
package com.sergibc.tmdb.data.repository.movie.datasource;

import android.content.Context;

import javax.inject.Inject;

/**
 * Data store factory for movies
 */
public class MovieDataStoreFactory {

    private final Context context;

    @Inject
    public MovieDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }

        this.context = context.getApplicationContext();
    }

    public MovieDataStore create(boolean refresh) {
        if (refresh) {
            return createCloudDataStore();
        }
        return createLocalDataStore();
    }

    private MovieLocalDataStore createLocalDataStore() {
        return new MovieLocalDataStore(context);
    }

    private MovieCloudDataStore createCloudDataStore() {
        return new MovieCloudDataStore(context);
    }
}

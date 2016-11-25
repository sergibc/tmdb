package com.sergibc.tmdb.navigation;

import com.sergibc.tmdb.internal.di.PerActivity;
import com.sergibc.tmdb.view.activity.BaseActivity;
import com.sergibc.tmdb.view.activity.MovieListActivity;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

/**
 * Class used to navigate through the application.
 */
@PerActivity
public class Navigator {

    @Inject
    public Navigator() {
    }

    public void goToMainActivity(BaseActivity activity, Bundle extras) {
        if (activity != null) {
            Intent intent = MovieListActivity.getCallingIntent(activity, extras);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
        }
    }

}
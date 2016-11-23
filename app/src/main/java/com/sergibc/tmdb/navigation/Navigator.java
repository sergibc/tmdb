package com.sergibc.tmdb.navigation;

import com.sergibc.tmdb.internal.di.PerActivity;
import com.sergibc.tmdb.view.activity.BaseActivity;
import com.sergibc.tmdb.view.activity.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

/**
 * Class used to navigate through the application.
 */
@PerActivity
public class Navigator {

    public static final int REQUEST_CODE_SELECTED_RESERVATION = 1;

    private Context context;

    @Inject
    public Navigator(Context context) {
        this.context = context;
    }

    public void goToMainActivity(BaseActivity activity, Bundle extras) {
        if (activity != null) {
            Intent intent = MainActivity.getCallingIntent(activity, extras);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
        }
    }

}
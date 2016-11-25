package com.sergibc.tmdb.view.activity;

import com.sergibc.tmdb.BuildConfig;
import com.sergibc.tmdb.TMDBApplication;
import com.sergibc.tmdb.data.util.preferences.PreferencesUtil;
import com.sergibc.tmdb.internal.di.component.ActivityComponent;
import com.sergibc.tmdb.internal.di.component.ApplicationComponent;
import com.sergibc.tmdb.internal.di.module.ActivityModule;
import com.sergibc.tmdb.navigation.Navigator;
import com.sergibc.tmdb.presenter.Presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Base {@link AppCompatActivity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Inject
    PreferencesUtil preferencesUtil;

    private ActivityComponent activityComponent;

    public abstract int getLayoutResourceId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        ScreenUtils.setExpectedScreenOrientation(this);

        int layoutResourceId = getLayoutResourceId();
        if (layoutResourceId > 0) {
            setContentView(layoutResourceId);
            ButterKnife.bind(this);
        }

        if (BuildConfig.DEBUG) {
            getSupportFragmentManager().addOnBackStackChangedListener(new ActivityBackStackChangedListener());
        }
    }

    protected abstract Presenter getPresenter();

    @Override
    protected void onResume() {
        super.onResume();

        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Method to obtain the main application component for dependency injection
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((TMDBApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Method to obtain an Activity module for dependency injection
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    private class ActivityBackStackChangedListener implements FragmentManager.OnBackStackChangedListener {

        private static final String TAG_START = "================= START ========================";

        private static final String TAG_END = "================= END =========================";

        private static final String TAG = "STACK";

        @Override
        public void onBackStackChanged() {
            Log.d(TAG, TAG_START);
            FragmentManager fm = getSupportFragmentManager();
            String stack = "";
            for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
                stack = stack + " --> " + getName(fm.getBackStackEntryAt(entry).getName());
            }
            Log.d(TAG, stack);
            Log.d(TAG, TAG_END);
        }

        private String getName(String name) {
            if (!name.isEmpty()) {
                String[] names = name.split("\\.");
                if (names.length == 0) {
                    return "";
                } else {
                    String lastOne = names[names.length - 1];
                    return lastOne;
                }
            } else {
                return "";
            }
        }
    }
}
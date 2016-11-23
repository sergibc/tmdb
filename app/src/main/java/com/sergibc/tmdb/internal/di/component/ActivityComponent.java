package com.sergibc.tmdb.internal.di.component;

import com.sergibc.tmdb.internal.di.PerActivity;
import com.sergibc.tmdb.internal.di.module.ActivityModule;
import com.sergibc.tmdb.view.activity.BaseActivity;

import android.app.Activity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation: {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Activity activity();
}
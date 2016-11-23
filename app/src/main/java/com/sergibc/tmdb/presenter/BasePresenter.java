package com.sergibc.tmdb.presenter;

import com.sergibc.tmdb.data.bus.RxBus;
import com.sergibc.tmdb.data.util.preferences.PreferencesUtil;
import com.sergibc.tmdb.navigation.Navigator;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
public abstract class BasePresenter implements Presenter {

    @Inject
    Navigator navigator;

    @Inject
    PreferencesUtil preferencesUtil;

    @Inject
    RxBus rxBus;

    private CompositeSubscription subscriptions;

    protected void subscribeToBus() {
        subscriptions = new CompositeSubscription();
        // TODO Add subscriptions
    }

    @Override
    public void destroy() {
        subscriptions.unsubscribe();
    }

}
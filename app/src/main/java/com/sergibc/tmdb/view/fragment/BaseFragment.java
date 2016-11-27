package com.sergibc.tmdb.view.fragment;

import com.sergibc.tmdb.data.util.preferences.PreferencesUtil;
import com.sergibc.tmdb.internal.di.HasComponent;
import com.sergibc.tmdb.navigation.Navigator;
import com.sergibc.tmdb.presenter.Presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

//import butterknife.ButterKnife;

/**
 * Base Fragment class for <b>every</b> fragment in this application.
 * <p>
 * <ul>
 * <li>Handles communication of the Fragment lifecycle to the {@link com.sergibc.tmdb.presenter.Presenter}.</li>
 * <li>And also performs {@link ButterKnife} binding and unbinding.</li>
 * </ul>
 */
public abstract class BaseFragment extends Fragment {

    @Inject
    Navigator navigator;

    @Inject
    PreferencesUtil preferencesUtil;

    //    protected Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        //        unbinder = ButterKnife.bind(this, view);
        //        ButterKnife.setDebug(BuildConfig.DEBUG);

        return view;
    }

    protected abstract int getLayoutResourceId();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeFragment(savedInstanceState);
    }

    /**
     * Method that should be implemented to initializeFragment fragment state or restore state
     *
     * @param savedInstanceState Bundle that contains the fragment's state
     */
    protected abstract void initializeFragment(Bundle savedInstanceState);

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        saveInstanceState(outState);
    }

    /**
     * Method that should be implemented to save the fragment state, the purpose of this method
     * <b>IT'S ONLY TO SAVE FRAGMENT STATE AS VIEW STUFF</b>
     *
     * @param outState Bundle where to put state
     */
    protected void saveInstanceState(Bundle outState) {

    }

    @Override
    public void onResume() {
        super.onResume();

        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.resume();
        }
    }

    protected abstract Presenter getPresenter();

    @Override
    public void onPause() {
        super.onPause();

        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.pause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //        unbinder.unbind();

        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
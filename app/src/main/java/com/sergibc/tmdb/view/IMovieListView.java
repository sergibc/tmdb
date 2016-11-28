package com.sergibc.tmdb.view;

import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;

/**
 * View interface for movie list screen
 */
public interface IMovieListView {

    void addMoviesToList(MovieResponseBo movieResponseBo);

    void hideEmptyView();

    void showEmptyView();

    void hideLoading();

    void showLoading();

    void showTypeThreeCharactersView();

    void hideTypeThreeCharactersView();
}

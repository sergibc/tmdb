package com.sergibc.tmdb.model.mapper;

import com.sergibc.tmdb.data.util.DateUtils;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieBo;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;
import com.sergibc.tmdb.domain.mapper.Mapper;
import com.sergibc.tmdb.model.MovieItemViewModel;
import com.sergibc.tmdb.model.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for view model of movies
 */

public class ViewModelMovieMapper implements Mapper<MovieResponseBo, MovieViewModel> {

    @Override
    public MovieViewModel modelToData(MovieResponseBo bo) {
        MovieViewModel viewModel = null;

        if (bo != null) {
            viewModel = new MovieViewModel();
            viewModel.setTotalResults(bo.getTotalResults());
            viewModel.setTotalPages(bo.getTotalPages());
            viewModel.setPage(bo.getPage());
            viewModel.setMovies(bosToViewModels(bo.getMovies()));
        }

        return viewModel;
    }

    @Override
    public MovieResponseBo dataToModel(MovieViewModel data) {
        // Not used for this coding test
        return null;
    }

    private List<MovieItemViewModel> bosToViewModels(List<MovieBo> bos) {
        List<MovieItemViewModel> viewModels = null;

        if (bos != null && !bos.isEmpty()) {
            viewModels = new ArrayList<>();
            for (MovieBo bo : bos) {
                viewModels.add(boToViewModel(bo));
            }
        }

        return viewModels;
    }

    private MovieItemViewModel boToViewModel(MovieBo bo) {
        MovieItemViewModel viewModel = null;

        if (bo != null) {
            viewModel = new MovieItemViewModel();
            viewModel.setId(bo.getId());
            viewModel.setOverview(bo.getOverview());
            viewModel.setImagePath(bo.getBackdropPath());
            viewModel.setTitle(bo.getTitle());
            viewModel.setYear(DateUtils.getYear(bo.getReleaseDate()));
        }

        return viewModel;
    }
}

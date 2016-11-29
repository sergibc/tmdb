package com.sergibc.tmdb.data.mapper.dto.movie;

import com.sergibc.tmdb.data.bean.movie.dto.MovieDto;
import com.sergibc.tmdb.data.bean.movie.dto.MovieResponse;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieBo;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;
import com.sergibc.tmdb.domain.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for movies
 */

public class MovieMapper implements Mapper<MovieResponse, MovieResponseBo> {

    @Override
    public MovieResponseBo modelToData(MovieResponse response) {
        MovieResponseBo bo = null;

        if (response != null) {
            bo = new MovieResponseBo();
            bo.setPage(response.getPage());
            bo.setTotalPages(response.getTotalPages());
            bo.setTotalResults(response.getTotalResults());
            bo.setMovies(modelToData(response.getMovies()));
        }

        return bo;
    }

    @Override
    public MovieResponse dataToModel(MovieResponseBo bo) {
        // We don't need for this coding test
        return null;
    }

    public List<MovieBo> modelToData(List<MovieDto> dtos) {
        List<MovieBo> bos = null;

        if (dtos != null) {
            bos = new ArrayList<>();
            for (MovieDto dto : dtos) {
                MovieBo bo = modelToData(dto);
                if (bo != null) {
                    bos.add(bo);
                }
            }
        }

        return bos;
    }


    public MovieBo modelToData(MovieDto dto) {
        MovieBo bo = null;

        if (dto != null) {
            bo = new MovieBo();
            bo.setPosterPath(dto.getPosterPath());
            bo.setAdult(dto.isAdult());
            bo.setOverview(dto.getOverview());
            bo.setReleaseDate(dto.getReleaseDate());
            bo.setGenreIds(dto.getGenreIds());
            bo.setId(dto.getId());
            bo.setOriginalTitle(dto.getOriginalTitle());
            bo.setOriginalLanguage(dto.getOriginalLanguage());
            bo.setTitle(dto.getTitle());
            bo.setBackdropPath(dto.getBackdropPath());
            bo.setPopularity(dto.getPopularity());
            bo.setVoteCount(dto.getVoteCount());
            bo.setVideo(dto.isVideo());
            bo.setVoteAverage(dto.getVoteAverage());
        }
        return bo;
    }

    // TODO: Remove
//    private MovieDto dataToModel(MovieBo bo) {
//        MovieDto dto = null;
//
//        if (bo != null) {
//            dto = new MovieDto();
//            dto.setPosterPath(bo.getPosterPath());
//            dto.setAdult(bo.isAdult());
//            dto.setOverview(bo.getOverview());
//            dto.setReleaseDate(bo.getReleaseDate());
//            dto.setGenreIds(bo.getGenreIds());
//            dto.setId(bo.getId());
//            dto.setOriginalTitle(bo.getOriginalTitle());
//            dto.setOriginalLanguage(bo.getOriginalLanguage());
//            dto.setTitle(bo.getTitle());
//            dto.setBackdropPath(bo.getBackdropPath());
//            dto.setPopularity(bo.getPopularity());
//            dto.setVoteCount(bo.getVoteCount());
//            dto.setVideo(bo.isVideo());
//            dto.setVoteAverage(bo.getVoteAverage());
//        }
//
//        return dto;
//    }
}

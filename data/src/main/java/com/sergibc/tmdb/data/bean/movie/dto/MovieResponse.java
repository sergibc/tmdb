package com.sergibc.tmdb.data.bean.movie.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.sergibc.tmdb.data.bean.movie.dto.MovieDto;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MovieResponse {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("results")
    @Expose
    private List<MovieDto> movies = new ArrayList<MovieDto>();

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    /**
     * @return The page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return The movies
     */
    public List<MovieDto> getMovies() {
        return movies;
    }

    /**
     * @param movies The movies
     */
    public void setMovies(List<MovieDto> movies) {
        this.movies = movies;
    }

    /**
     * @return The totalResults
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The total_results
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @return The totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The total_pages
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}

package com.sergibc.tmdb.data.net;

import com.sergibc.tmdb.data.bean.movie.dto.MovieResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Api of services
 */
public interface Api {

    /**
     * Get popular movies
     *
     * @param apiKey   String value of the API_KEY
     * @param language String value of the language
     * @param page     int value of the page requested
     */
    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    /**
     * Search movies by a text query
     *
     * @param apiKey   String value of the API_KEY
     * @param language String value of the language
     * @param query    String value of query
     * @param page     int value of the page requested
     */
    @GET("search/movie")
    Observable<MovieResponse> searchMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") int page
    );
}

package com.sergibc.tmdb.data.mapper.dto.movie;

import com.sergibc.tmdb.data.bean.movie.dto.MovieDto;
import com.sergibc.tmdb.data.bean.movie.dto.MovieResponse;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieBo;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

/**
 * Test for mapper the movies
 */
public class MovieMapperTest {

    private static final int FAKE_GENRE_ID = 1;

    private static final int FAKE_PAGE = 1;

    private static final int FAKE_TOTAL_PAGES = 2;

    private static final int FAKE_TOTAL_RESULTS = 3;

    private static final String FAKE_POSTER_PATH = "fake_poster_path";

    private static final String FAKE_OVER_VIEW = "fake_over_view";

    private static final String FAKE_RELEASE_DATE = "fake_release_date";

    private static final String FAKE_ORIGINAL_TITLE = "fake_original_title";

    private static final String FAKE_ORIGINAL_LANGUAGE = "fake_original_language";

    private static final String FAKE_TITLE = "fake_title";

    private static final String FAKE_BACKDROP_PATH = "fake_backdrop_path";

    private static final int FAKE_ID = 1;

    private static final int FAKE_VOTE_COUNT = 1;

    private static final int FAKE_VOTE_AVERAGE = 1;

    private static final boolean FAKE_VIDEO = false;

    private static final boolean FAKE_ADULT = false;

    private static final double FAKE_POPULARITY = 1d;

    private MovieMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new MovieMapper();
    }

    @Test
    public void null_model_should_return_null_data() {
        //Given
        MovieResponse model = null;

        //When
        MovieResponseBo data = mapper.modelToData(model);

        //Then
        assertNull(data);
    }

    @Test
    public void null_data_should_return_null_model() {
        //Given
        MovieResponseBo data = null;

        //When
        MovieResponse model = mapper.dataToModel(data);

        //Then
        assertNull(model);
    }

    @Test
    public void model_values_should_be_same_data_values() {
        // Given
        MovieResponse model = createFakeModel();

        // When
        MovieResponseBo data = mapper.modelToData(model);

        // Then
        assertEquals(model.getPage(), data.getPage());
        assertEquals(model.getTotalPages(), data.getTotalPages());
        assertEquals(model.getTotalResults(), data.getTotalResults());
    }

    @Test
    public void size_list_of_movies_should_return_same_size() {
        //Given
        List<MovieDto> movieDtos = new ArrayList<>();
        movieDtos.add(mock(MovieDto.class));
        movieDtos.add(mock(MovieDto.class));
        movieDtos.add(mock(MovieDto.class));

        // When
        List<MovieBo> movieBos = mapper.modelToData(movieDtos);

        // Then
        assertEquals(movieDtos.size(), movieBos.size());
    }

    @Test
    public void model_movie_should_be_same_data_movie() {
        //Given
        MovieDto model = createFakeMovieModel();

        //When
        MovieBo data = mapper.modelToData(model);

        //Then
        assertEquals(model.getPosterPath(), data.getPosterPath());
        assertEquals(model.isAdult(), data.isAdult());
        assertEquals(model.getOverview(), data.getOverview());
        assertEquals(model.getReleaseDate(), data.getReleaseDate());
        assertEquals(model.getId(), data.getId());
        assertEquals(model.getOriginalTitle(), data.getOriginalTitle());
        assertEquals(model.getOriginalLanguage(), data.getOriginalLanguage());
        assertEquals(model.getTitle(), data.getTitle());
        assertEquals(model.getBackdropPath(), data.getBackdropPath());
        assertEquals(model.getPopularity(), data.getPopularity());
        assertEquals(model.getVoteCount(), data.getVoteCount());
        assertEquals(model.isVideo(), data.isVideo());
        assertEquals(model.getVoteAverage(), data.getVoteAverage());
        assertEquals(model.getGenreIds().size(), data.getGenreIds().size());
    }

    private MovieDto createFakeMovieModel() {
        MovieDto model = new MovieDto();
        model.setPosterPath(FAKE_POSTER_PATH);
        model.setAdult(FAKE_ADULT);
        model.setOverview(FAKE_OVER_VIEW);
        model.setReleaseDate(FAKE_RELEASE_DATE);
        model.setId(FAKE_ID);
        model.setOriginalTitle(FAKE_ORIGINAL_TITLE);
        model.setOriginalLanguage(FAKE_ORIGINAL_LANGUAGE);
        model.setTitle(FAKE_TITLE);
        model.setBackdropPath(FAKE_BACKDROP_PATH);
        model.setPopularity(FAKE_POPULARITY);
        model.setVoteCount(FAKE_VOTE_COUNT);
        model.setVideo(FAKE_VIDEO);
        model.setVoteAverage(FAKE_VOTE_AVERAGE);

        List<Integer> genreIds = new ArrayList<>();
        genreIds.add(FAKE_GENRE_ID);
        model.setGenreIds(genreIds);

        return model;
    }

    private MovieResponse createFakeModel() {
        MovieResponse model = new MovieResponse();
        model.setPage(FAKE_PAGE);
        model.setTotalPages(FAKE_TOTAL_PAGES);
        model.setTotalResults(FAKE_TOTAL_RESULTS);
        return model;
    }

}
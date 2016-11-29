package com.sergibc.tmdb.model.mapper;

import com.sergibc.tmdb.domain.bean.interactor.movie.MovieBo;
import com.sergibc.tmdb.domain.bean.interactor.movie.MovieResponseBo;
import com.sergibc.tmdb.model.MovieItemViewModel;
import com.sergibc.tmdb.model.MovieViewModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

/**
 * Test for view movie model mappers
 */
public class ViewModelMovieMapperTest {

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

    private static final int FAKE_GENRE_ID = 1;

    private ViewModelMovieMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ViewModelMovieMapper();
    }

    @Test
    public void null_data_should_return_null_view_model() {
        //Given
        MovieResponseBo data = null;

        //When
        MovieViewModel viewModel = mapper.modelToData(data);

        //Then
        assertNull(viewModel);
    }

    @Test
    public void null_view_model_should_return_null_datal() {
        //Given
        MovieViewModel viewModel = null;

        //When
        MovieResponseBo data = mapper.dataToModel(viewModel);

        //Then
        assertNull(data);
    }

    @Test
    public void data_values_should_be_same_view_model_values() {
        // Given
        MovieResponseBo bo = createFakeData();

        // When
        MovieViewModel viewModel = mapper.modelToData(bo);

        // Then
        assertEquals(viewModel.getPage(), bo.getPage());
        assertEquals(viewModel.getTotalPages(), bo.getTotalPages());
        assertEquals(viewModel.getTotalResults(), bo.getTotalResults());
    }

    @Test
    public void size_list_of_movies_should_return_same_size() {
        //Given
        List<MovieBo> movieBos = new ArrayList<>();
        movieBos.add(mock(MovieBo.class));
        movieBos.add(mock(MovieBo.class));
        movieBos.add(mock(MovieBo.class));

        // When
        List<MovieItemViewModel> movieItemViewModels = mapper.bosToViewModels(movieBos);

        // Then
        assertEquals(movieItemViewModels.size(), movieBos.size());
    }

    @Test
    public void data_movie_should_be_same_model_view_movie() {
        //Given
        MovieBo data = createFakeMovieModel();

        //When
        MovieItemViewModel movieItemViewModel = mapper.boToViewModel(data);

        //Then
        assertEquals(movieItemViewModel.getOverview(), data.getOverview());
        //assertEquals(movieItemViewModel.getYear(), data.getReleaseDate());  // TODO year should be tested isolated
        assertEquals(movieItemViewModel.getId(), data.getId());
        assertEquals(movieItemViewModel.getTitle(), data.getTitle());
        assertEquals(movieItemViewModel.getImagePath(), data.getBackdropPath());
    }

    private MovieBo createFakeMovieModel() {
        MovieBo bo = new MovieBo();
        bo.setPosterPath(FAKE_POSTER_PATH);
        bo.setAdult(FAKE_ADULT);
        bo.setOverview(FAKE_OVER_VIEW);
        bo.setReleaseDate(FAKE_RELEASE_DATE);
        bo.setId(FAKE_ID);
        bo.setOriginalTitle(FAKE_ORIGINAL_TITLE);
        bo.setOriginalLanguage(FAKE_ORIGINAL_LANGUAGE);
        bo.setTitle(FAKE_TITLE);
        bo.setBackdropPath(FAKE_BACKDROP_PATH);
        bo.setPopularity(FAKE_POPULARITY);
        bo.setVoteCount(FAKE_VOTE_COUNT);
        bo.setVideo(FAKE_VIDEO);
        bo.setVoteAverage(FAKE_VOTE_AVERAGE);

        List<Integer> genreIds = new ArrayList<>();
        genreIds.add(FAKE_GENRE_ID);
        bo.setGenreIds(genreIds);

        return bo;
    }

    private MovieResponseBo createFakeData() {
        MovieResponseBo model = new MovieResponseBo();
        model.setPage(FAKE_PAGE);
        model.setTotalPages(FAKE_TOTAL_PAGES);
        model.setTotalResults(FAKE_TOTAL_RESULTS);
        return model;
    }

}
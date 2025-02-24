package com.task.movietask.Services;
import com.task.movietask.Services.dto.OmdbMovieDto;
import com.task.movietask.entityclasses.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAllMovies();
    Movie findByImdbId(String imdbId);

    OmdbMovieDto getMovieById(String imdbId);
    Movie addMovie(Movie movie);
    void deleteMovie(String imdbId);

     OmdbMovieDto getMovieDetails(String imdbId);

    OmdbMovieDto getMovieByTitleYearPlot(String title, String year, String plot);

    void deleteMovies(List<String> imdbIds);

    Page<Movie> searchMovies(String title, Pageable pageable);
}

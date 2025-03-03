package com.task.movietask.Services.Impl;

import com.task.movietask.Repository.MovieRepository;
import com.task.movietask.Services.MovieService;
import com.task.movietask.Services.dto.OmdbMovieDto;
import com.task.movietask.entityclasses.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class MovieServiceImpl implements MovieService {

    @Value("${omdb.api.key}")
    private String omdbApiKey;

    @Value("${omdb.url.integration}")
    private String omdbUrlIntegration;

    private List<Movie> movieList = new ArrayList<>();

    @Autowired
    private MovieRepository movieRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findByImdbId(String imdbId) {
        return movieRepository.findByImdbID(imdbId).orElse(null);
    }

    @Override
    public OmdbMovieDto getMovieById(String imdbId) {
        Optional<Movie> movie = movieRepository.findByImdbID(imdbId);
        OmdbMovieDto omdbMovieDto=new OmdbMovieDto();
        if (movie.isPresent()){
            omdbMovieDto.setTitle(movie.get().getTitle());
            omdbMovieDto.setImdbID(movie.get().getImdbID());
            omdbMovieDto.setGenre(movie.get().getGenre());
        }
        return omdbMovieDto;
    }


    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
        }



    @Override
    public void deleteMovie(String imdbId) {
        Movie movie = findByImdbId(imdbId);
        if (movie != null) {
            movieRepository.delete(movie);
        } else {
            throw new RuntimeException("Movie not found!");
        }
    }


    public OmdbMovieDto getMovieDetails(String imdbId) {
        String url = omdbUrlIntegration + "?i=" + imdbId + "&apikey=" + omdbApiKey;
        return restTemplate.getForObject(url, OmdbMovieDto.class);
    }

    @Override
    public OmdbMovieDto getMovieByTitleYearPlot(String title, String year, String plot) {

        String url = omdbUrlIntegration +
                "?apikey=" + omdbApiKey +
                "&t=" + title +
                "&y=" + year +
                "&plot=" + plot;
        return restTemplate.getForObject(url, OmdbMovieDto.class);
    }

    @Override
    @Transactional
    public void deleteMovies(List<String> imdbIds) {
        for (String imdbId : imdbIds) {
            movieRepository.deleteByImdbID(imdbId);
        }
    }

    public Page<Movie> searchMovies(String title, Pageable pageable) {
        return movieRepository.findByTitleContainingIgnoreCase(title, pageable);
    }




}


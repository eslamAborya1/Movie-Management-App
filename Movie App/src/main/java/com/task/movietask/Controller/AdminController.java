package com.task.movietask.Controller;

import com.task.movietask.Services.MovieService;
import com.task.movietask.Services.dto.OmdbMovieDto;
import com.task.movietask.entityclasses.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    @Autowired
    private MovieService movieService;

//    @GetMapping("/search-omdb")
//    public ResponseEntity<List<Movie>> searchOmdbMovies(@RequestParam String title) {
//        List<Movie> movies = omdbService.searchMovies(title);
//        return ResponseEntity.ok(movies);
//    }





//    @PostMapping("/add-movie")
//    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
//
//        try {
//            movieService.addMovie(movie);
//            return ResponseEntity.ok("Movie added successfully!");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }


        //        try {
//            movieService.addMovie(imdbId, title, year, genre);
//            return ResponseEntity.ok("Movie added successfully!");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }


    @DeleteMapping("/remove-movie/{imdbId}")
    public ResponseEntity<String> removeMovie(@PathVariable String imdbId) {
        try {
            movieService.deleteMovie(imdbId);
            return ResponseEntity.ok("Movie removed successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/omdb-movie/{imdbId}")
    public ResponseEntity<OmdbMovieDto> getMovieDetails(@PathVariable String imdbId) {
        OmdbMovieDto movieDetails = movieService.getMovieDetails(imdbId);
        if (movieDetails != null) {
            return ResponseEntity.ok(movieDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movie/{imdbId}")
    public ResponseEntity<OmdbMovieDto> getMovie(@PathVariable String imdbId) {
        OmdbMovieDto movieDetails = movieService.getMovieById(imdbId);
        if (movieDetails != null) {
            return ResponseEntity.ok(movieDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/omdb-movie")
    public ResponseEntity<?> getMovie(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String plot) {

        OmdbMovieDto movie = movieService.getMovieByTitleYearPlot(title, year, plot);
        if (movie != null && "True".equalsIgnoreCase(movie.getResponse())) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.badRequest().body(movie != null ? movie.getResponse(): "Error retrieving movie");
        }
    }


    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovies(@RequestBody Movie movies) {
        try {
            movieService.addMovie(movies);
            return ResponseEntity.ok("Movie(s) added successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remove-movie")
    public ResponseEntity<String> removeMovies(@RequestBody List<String> imdbIds) {
        try {
            movieService.deleteMovies(imdbIds);
            return ResponseEntity.ok("Movie(s) removed successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        List<Movie>movies= movieService.findAllMovies();
        return movies;
    }

    @GetMapping("/movies/search")
    public Page<Movie> searchMovies(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieService.searchMovies(title, pageable);
    }



}
package com.task.movietask.Services.Impl;

import com.task.movietask.Repository.*;
import com.task.movietask.Services.*;
import com.task.movietask.entityclasses.*;
import com.task.movietask.securityConfig.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingServiceImpl implements RatingService {

        @Autowired
        private RatingRepository ratingRepository;

        @Autowired
        private UserService userService;

        @Autowired
        private MovieService movieService;

        @Override
        public Rate rateMovie(Long userId, String imdbId, Double rate) {
            User user = userService.getUserByUsername(userId.toString());
            if (user == null) {
                throw new RuntimeException("User not found!");
            }

            Movie movie = movieService.findByImdbId(imdbId);
            if (movie == null) {
                throw new RuntimeException("Movie not found!");
            }

            Rate existingRating = ratingRepository.findByUserAndMovie(user, movie).orElse(null);
            if (existingRating != null) {
                existingRating.setRating(rate);
                return ratingRepository.save(existingRating);
            } else {
                Rate newRating = new Rate();
                newRating.setUser(user);
                newRating.setMovie(movie);
                newRating.setRating(rate);
                return ratingRepository.save(newRating);
            }
        }

        @Override
        public List<Rate> getRatingsByUser(Long userId) {
            User user = userService.getUserByUsername(userId.toString());
            if (user == null) {
                throw new RuntimeException("User not found!");
            }
            return ratingRepository.findByUser(user);
        }

        @Override
        public List<Rate> getRatingsByMovie(String imdbId) {
            Movie movie = movieService.findByImdbId(imdbId);
            if (movie == null) {
                throw new RuntimeException("Movie not found!");
            }
            return ratingRepository.findByMovie(movie);
        }
    }

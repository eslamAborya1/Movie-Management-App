package com.task.movietask.Services;

import com.task.movietask.entityclasses.Rate;

import java.util.List;

public interface RatingService {
    Rate rateMovie(Long userId, String imdbId, Double rate);
    List<Rate> getRatingsByUser(Long userId);
    List<Rate> getRatingsByMovie(String imdbId);
}

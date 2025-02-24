package com.task.movietask.Repository;

import com.task.movietask.entityclasses.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rate,Long> {

    List<Rate> findByUser(User user);
    List<Rate> findByMovie(Movie movie);
    Optional<Rate> findByUserAndMovie(User user, Movie movie);
}

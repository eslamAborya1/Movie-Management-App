package com.task.movietask.Repository;

import com.task.movietask.entityclasses.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    Optional<Movie> findByImdbID(String imdbId);
    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    void deleteByImdbID(String imdbId);

}


package com.example.cienemaadminapi.repository;

import com.example.cienemaadminapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaAdminAPIMovieRepository extends JpaRepository<Movie,Long> {
}

package com.example.cienemaadminapi.services;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie movie) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            movieOptional.get().setTitle(movie.getTitle());
            movieOptional.get().setDescription(movie.getDescription());
            return movieRepository.save(movieOptional.get());
        }
        return null;
    }

    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }
}

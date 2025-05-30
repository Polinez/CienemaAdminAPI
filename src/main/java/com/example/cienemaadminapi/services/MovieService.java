package com.example.cienemaadminapi.services;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//    public Movie updateMovie(Long id, Movie movie) {
//        Optional<Movie> movieOptional = movieRepository.findById(id);
//        if (movieOptional.isPresent()) {
//            movieOptional.get().setTitle(movie.getTitle());
//            movieOptional.get().setDescription(movie.getDescription());
//            return movieRepository.save(movieOptional.get());
//        }
//        return null;
//    }
    public Movie updateMovie(Long id, Movie updatedMovie) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setTitle(updatedMovie.getTitle());
                    movie.setDirector(updatedMovie.getDirector());
                    movie.setDescription(updatedMovie.getDescription());
                    movie.setDuration(updatedMovie.getDuration());
                    return movieRepository.save(movie);
                })
                .orElseThrow(() -> new RuntimeException("Nie znaleziono filmu o ID: " + id));
    }

    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    //trying to make sorting methods
    public List<Movie> findMoviesWithSorting(String field, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return movieRepository.findAll(Sort.by(sortDirection, field));
    }

    public Page<Movie> findMoviesWithPagination(int offset, int pageSize){
        Page<Movie> movies = movieRepository.findAll(PageRequest.of(offset, pageSize));
        return movies;
    }

    public Page<Movie> findMoviesWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Movie> movies = movieRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return movies;
    }

    public Page<Movie> findMoviesWithPaginationAndSorting(int offset, String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();
        Page<Movie> movies = movieRepository.findAll(PageRequest.of(offset, 5, sort));
        return movies;
    }


}
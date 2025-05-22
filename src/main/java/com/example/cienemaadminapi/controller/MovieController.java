package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public String movies() {
        return "movies";
    }

    @GetMapping("/movies/add")
    public String addMovie() {
        return "addMovie";
    }

    //@PutMapping("/movies/update/{id}")
    //public Movie updateMovie(@PathVariable Long id, @RequestBody Movie newMovie) {
    //    return movieService.getMovieById(id)
    //            .map(movie -> {
    //                movie.setTitle(newMovie.getTitle());
    //                movie.setDescription(newMovie.getDescription());
     //               movie.setDirector(newMovie.getDirector());
     //               movie.setProjection(newMovie.getProjection());
     //               return movieService.addMovie(movie);
     //           })
     //           .orElseThrow();
    //}
}

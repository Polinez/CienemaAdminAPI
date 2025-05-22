package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String movies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
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

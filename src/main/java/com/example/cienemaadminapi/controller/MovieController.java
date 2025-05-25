package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/movies/edit")
    public String editMovie() {
        return "editMovie";
    }

    @GetMapping("movies/delete")
    public String deleteMovie() {return "deleteMovie";}
}

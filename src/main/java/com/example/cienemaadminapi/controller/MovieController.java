package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class MovieController {

    @GetMapping("/movies")
    public String movies(Model model) {
        List<Movie> movies = MovieService.findAllMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }
}

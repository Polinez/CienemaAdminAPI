package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public String getMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movies/add")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "addMovie";
    }

    @PostMapping("/movies/add")
    public String addMovie(@ModelAttribute("movie") Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/admin/movies";
    }

    //sorting data by fields
    @GetMapping("/movies/{field}")
    public String getMoviesWithSorting(@PathVariable String field, Model model) {
        List<Movie> movies = movieService.findMoviesWithSorting(field);
        model.addAttribute("movies", movies);
        return "movies";
    }

    //page pagination
    @GetMapping("/movies/pagination/{offset}/{pageSize}")
    public String getMoviesWithPagination(@PathVariable int offset, @PathVariable int pageSize, Model model) {
        Page<Movie> moviesWithPagination = movieService.findMoviesWithPagination(offset, pageSize);
        model.addAttribute("movies", moviesWithPagination);
        return "movies";
    }

    //page pagination and sorting at the same time
    @GetMapping("/movies/paginationAndSort/{offset}/{pageSize}/{field}")
    public String getMoviesWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field, Model model) {
        Page<Movie> moviesWithPaginationAndSorting = movieService.findMoviesWithPaginationAndSorting(offset, pageSize, field);
        model.addAttribute("movies", moviesWithPaginationAndSorting);
        return "movies";
    }
}

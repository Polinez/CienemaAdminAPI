package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class MovieController {

    private final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private MovieService movieService;


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

    @DeleteMapping("/movies/delete")
    public String deleteMovie(@ModelAttribute("movie") Movie movie) {
        movieService.deleteMovie(movie);
        return "redirect:/admin/movies";
    }

    //please add an HTML file when created
    @GetMapping("/movies/update")
    public String updateMovie(@ModelAttribute("movie") Movie movie) {
        return "updateMovie";
    }

    @PutMapping("/movie/update/{id}")
    public String updateMovie(@PathVariable Long id, @RequestBody Movie newMovie) {
        return movieService.getMovieById(id)
                .map(movie -> {
                    movie.setTitle(newMovie.getTitle());
                    movie.setDescription(newMovie.getDescription());
                    movie.setDirector(newMovie.getDirector());
                    movie.setDuration(newMovie.getDuration());
                    movieService.addMovie(movie);
                    return "redirect:/movies";
                })
                .orElseThrow();
    }


    //page pagination and sorting at the same time
    @GetMapping("/movies/{field}/{direction}/{offset}/{pageSize}")
    public String getMoviesWithPaginationAndSorting(@PathVariable String field,
                                                    @PathVariable String direction,
                                                    @PathVariable int offset,
                                                    @PathVariable int pageSize,
                                                    Model model) {
        Page<Movie> moviesPage = movieService.findMoviesWithPaginationAndSorting(offset, pageSize, field, direction);

        // Dane do widoku
        model.addAttribute("movies", moviesPage.getContent());  // Filmy
        model.addAttribute("currentPage", moviesPage.getNumber());  // Aktualna strona (offset)
        model.addAttribute("totalPages", moviesPage.getTotalPages());  // Całkowita liczba stron
        model.addAttribute("totalItems", moviesPage.getTotalElements());  // Całkowita liczba elementów
        model.addAttribute("pageSize", moviesPage.getSize());  // Rozmiar strony
        model.addAttribute("sortField", field);  // Pole sortowania
        model.addAttribute("sortDirection", direction);  // Kierunek sortowania
        model.addAttribute("reverseSortDirection", direction.equals("asc") ? "desc" : "asc");  // Odwrotny kierunek sortowania

        return "movies";
    }
}

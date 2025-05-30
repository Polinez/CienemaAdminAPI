package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return "redirect:/admin/movies/title/asc/0";
    }

    @DeleteMapping("/movies/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        try {
            movieService.deleteMovieById(id);
            return ResponseEntity.ok("Film został usunięty pomyślnie");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Wystąpił błąd podczas usuwania filmu: " + e.getMessage());
        }
    }

    @GetMapping("/movies/update")
    public String updateMovie(@ModelAttribute("movie") Movie movie, Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "updateMovie";
    }

//    @PutMapping("/movie/update/{id}")
//    public String updateMovie(@PathVariable Long id, @RequestBody Movie newMovie) {
//        return movieService.getMovieById(id)
//                .map(movie -> {
//                    movie.setTitle(newMovie.getTitle());
//                    movie.setDescription(newMovie.getDescription());
//                    movie.setDirector(newMovie.getDirector());
//                    movie.setDuration(newMovie.getDuration());
//                    movieService.addMovie(movie);
//                    return "redirect:/movies";
//                })
//                .orElseThrow();
//    }
    @GetMapping("/movies/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono filmu o ID: " + id));
        model.addAttribute("movie", movie);
        return "editMovie";
    }

    @PostMapping("/movies/update/{id}")
    public String updateMovieWithId(@PathVariable Long id, @ModelAttribute("movie") Movie updatedMovie) {
        movieService.updateMovie(id, updatedMovie);
        return "redirect:/admin/movies";
    }


    //page pagination and sorting at the same time
    @GetMapping("/movies/{field}/{direction}/{offset}")
    public String getMoviesWithPaginationAndSorting(@PathVariable String field,
                                                    @PathVariable String direction,
                                                    @PathVariable int offset,
                                                    Model model) {
        Page<Movie> moviesPage = movieService.findMoviesWithPaginationAndSorting(offset, field, direction);

        // Dane do widoku
        model.addAttribute("movies", moviesPage.getContent());  // Filmy
        model.addAttribute("currentPage", moviesPage.getNumber());  // Aktualna strona (offset)
        model.addAttribute("totalPages", moviesPage.getTotalPages());  // Całkowita liczba stron
        model.addAttribute("sortField", field);  // Pole sortowania
        model.addAttribute("sortDirection", direction);  // Kierunek sortowania
        model.addAttribute("reverseSortDirection", direction.equals("asc") ? "desc" : "asc");  // Odwrotny kierunek sortowania

        return "movies";
    }
}

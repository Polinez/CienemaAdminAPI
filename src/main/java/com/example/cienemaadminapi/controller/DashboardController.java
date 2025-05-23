package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.model.Reservation;
import com.example.cienemaadminapi.services.MovieService;
import com.example.cienemaadminapi.services.ProjectionService;
import com.example.cienemaadminapi.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ProjectionService projectionService;
    @Autowired
    private MovieService movieService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        LocalDate today = LocalDate.now();

        List<Reservation> todayReservations = reservationService.getReservationsForDate(today);
        List<Projection> todaysProjections = projectionService.findProjectionsByLocalDate(today);
        List<Movie> allMovies = movieService.getAllMovies();

        int moviesCount = allMovies.size();
        int projectionCount = todaysProjections.size();
        int reservationCount = todayReservations.size();
        Double totalIncome = todayReservations.stream()
                .mapToDouble(Reservation::getPrice)
                .sum();

        model.addAttribute("reservationCount", reservationCount);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("projectionCount", projectionCount);
        model.addAttribute("moviesCount", moviesCount);

        return "dashboard";
    }
}

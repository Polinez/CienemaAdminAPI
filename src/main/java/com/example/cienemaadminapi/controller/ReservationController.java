package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.model.Reservation;
import com.example.cienemaadminapi.services.ProjectionService;
import com.example.cienemaadminapi.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class ReservationController {
    @Autowired
    private ProjectionService projectionService;
    @Autowired
    private ReservationService reservationService;

    //page pagination and sorting at the same time
    @GetMapping("/reservations/{projectionId}/{field}/{direction}/{offset}")
    public String getReservationsWithPaginationAndSorting(@PathVariable("projectionId") Long projectionId,
                                                          @PathVariable String field,
                                                          @PathVariable String direction,
                                                          @PathVariable int offset,
                                                          Model model) {
        Optional<Projection> optionalProjection = projectionService.getProjectionById(projectionId);

        if (optionalProjection.isPresent()) {
            Projection projection = optionalProjection.get();
            Page<Reservation> reservationsPage = reservationService.findReservationsWithPaginationAndSorting(projectionId, offset, field, direction);

            model.addAttribute("projection", projection);
            model.addAttribute("reservations", reservationsPage.getContent()); // zawartosc
            model.addAttribute("currentPage", reservationsPage.getNumber()); // aktualna strona
            model.addAttribute("totalPages", reservationsPage.getTotalPages()); // liczba stron
            model.addAttribute("sortField", field); // pole sortowania
            model.addAttribute("sortDirection", direction);// kierunek sortowania
            model.addAttribute("reverseSortDirection", direction.equals("asc") ? "desc" : "asc");

            return "reservations";
        } else {
            return "redirect:/admin/projections/movie.title/asc/0";
        }
    }
}
package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Reservation;
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

@Controller
@RequestMapping("/admin")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public String getReservationById(@RequestParam(name = "id") Long id, Model model) {
        List<Reservation> reservations = reservationService.getReservationsByProjectionId(id);
        model.addAttribute("reservation", reservations);
        return "reservations";
    }


    @GetMapping("/reservations/{field}")
    public String getReservationsWithSorting(@PathVariable String field, Model model) {
        List<Reservation> reservations = reservationService.findReservationWithSorting(field);
        model.addAttribute("reservations", reservations);
        return "reservations";
    }

    //page pagination
    @GetMapping("/reservations/pagination/{offset}/{pageSize}")
    public String getReservationsWithPagination(@PathVariable int offset, @PathVariable int pageSize, Model model) {
        Page<Reservation> reservationWithPagination = reservationService.findReservationsWithPagination(offset, pageSize);
        model.addAttribute("reservations", reservationWithPagination);
        return "reservations";
    }

    //page pagination and sorting at the same time
    @GetMapping("/reservations/paginationAndSort/{offset}/{pageSize}/{field}")
    public String getReservationsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field, Model model) {
        Page<Reservation> reservationsWithPaginationAndSorting = reservationService.findReservationsWithPaginationAndSorting(offset, pageSize, field);
        model.addAttribute("reservations", reservationsWithPaginationAndSorting);
        return "reservations";
    }
}

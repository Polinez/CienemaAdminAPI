package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Reservation;
import com.example.cienemaadminapi.services.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public String reservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservations";
    }

    @GetMapping("/reservations/{field}")
    public String getReservationsWithSorting(@PathVariable String field, Model model) {
        List<Reservation> reservations = reservationService.findReservationWithSorting(field);
        model.addAttribute("reservations", reservations);
        return "reservations";
    }
}

package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Reservation;
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

    //@RequestMapping("/dashboard")
   // public String dashboard() {
    //    return "dashboard";
    //}

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        LocalDate today = LocalDate.now();

        List<Reservation> todayReservations = reservationService.getReservationsForDate(today);

        int reservationCount = todayReservations.size();
        Double totalIncome = todayReservations.stream()
                .mapToDouble(Reservation::getPrice)
                .sum();

        model.addAttribute("reservationCount", reservationCount);
        model.addAttribute("totalIncome", totalIncome);

        return "dashboard";
    }
}

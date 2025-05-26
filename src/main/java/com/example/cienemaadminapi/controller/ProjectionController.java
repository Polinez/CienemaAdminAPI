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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ProjectionController {

    @Autowired
    private ProjectionService projectionService;
    @Autowired
    private ReservationService reservationService;


    @GetMapping("/projections/add")
    public String addProjection(Model model) {
        model.addAttribute("projection", new Projection());
        return "addProjection";
    }

    @PostMapping("/projections/add")
    public String addProjection(@ModelAttribute("projection") Projection projection) {
        projectionService.addProjection(projection);
        return "redirect:/projections";
    }

    @DeleteMapping("/projections/delete")
    public String deleteProjection(@ModelAttribute("projection") Projection projection) {
        projectionService.deleteProjectionById(projection.getId());
        return "redirect:/projections";
    }

    //please add html file when created
    @GetMapping("/projections/update")
    public String updateProjection(@ModelAttribute("projection") Projection projection) {

        return "updateProjection";
    }

    @PutMapping("/projections/update/{id}")
    public String updateProjection(@PathVariable Long id, @RequestBody Projection newProjection) {
        return projectionService.getProjectionById(id)
                .map(projection -> {
                    projection.setMovie(newProjection.getMovie());
                    projection.setDate(newProjection.getDate());
                    projection.setDate(newProjection.getStartTime());
                    projection.setRoomNumber(newProjection.getRoomNumber());
                    projectionService.addProjection(projection);
                    return "redirect:/projections";
                })
                .orElseThrow();
    }

    @GetMapping("/projections/{id}/reservations")
    public String getProjectionReservations(@PathVariable Long id, Model model) {
        Optional<Projection> optionalProjection = projectionService.getProjectionById(id);

        if (optionalProjection.isPresent()) {
            Projection projection = optionalProjection.get();
            List<Reservation> reservations = reservationService.getReservationsByProjectionId(id);;
            model.addAttribute("projection", projection);
            model.addAttribute("reservations", reservations);
            return "reservations";
        } else {
            return "redirect:/projections";
        }
    }

    //page pagination
    @GetMapping("/projections/{field}/{direction}/{offset}")
    public String getProjectionsWithPaginationAndSorting(@PathVariable String field,
                                                         @PathVariable String direction,
                                                         @PathVariable int offset,
                                                         Model model) {
        Page<Projection> page = projectionService.findProjectionsWithPaginationAndSorting(offset, field, direction);

        model.addAttribute("projections", page.getContent());
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("sortField", field);
        model.addAttribute("sortDirection", direction);
        model.addAttribute("reverseSortDirection", direction.equals("asc") ? "desc" : "asc");

        return "projections";
    }
}

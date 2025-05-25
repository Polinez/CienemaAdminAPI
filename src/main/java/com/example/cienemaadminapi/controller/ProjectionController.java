package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.model.Reservation;
import com.example.cienemaadminapi.services.ProjectionService;
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

    @GetMapping("/projections")
    public String projections(Model model) {
        List<Projection> projections = projectionService.getAllProjections();
        model.addAttribute("projections", projections);
        return "projections";
    }

    @GetMapping("/projections/{field}")
    public String getProjectionsWithSorting(@PathVariable String field, Model model) {
        List<Projection> projections = projectionService.findProjectionWithSorting(field);
        model.addAttribute("projections", projections);
        return "projections";
    }

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
        Optional<Projection> projections = projectionService.getProjectionById(id);
        if (projections.isPresent()) {
            Projection projection = projections.get();
            List<Reservation> reservations = projection.getReservations();
            model.addAttribute("reservations", reservations);
            model.addAttribute("projection", projection);
            return "projectionReservations";
        } else {
            return "redirect:/projections";
        }
    }

    //page pagination
    @GetMapping("/projections/pagination/{offset}/{pageSize}")
    public String getProjectionsWithPagination(@PathVariable int offset, @PathVariable int pageSize, Model model) {
        Page<Projection> projectionsWithPagination = projectionService.findProjectionsWithPagination(offset, pageSize);
        model.addAttribute("projections", projectionsWithPagination);
        return "projections";
    }

    //page pagination and sorting at the same time
    @GetMapping("/projections/paginationAndSort/{offset}/{pageSize}/{field}")
    public String getProjectionsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field, Model model) {
        Page<Projection> projectionsWithPaginationAndSorting = projectionService.findProjectionsWithPaginationAndSorting(offset, pageSize, field);
        model.addAttribute("projections", projectionsWithPaginationAndSorting);
        return "projections";
    }
}

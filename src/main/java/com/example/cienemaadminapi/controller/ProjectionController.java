package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.model.Reservation;
import com.example.cienemaadminapi.repository.MovieRepository;
import com.example.cienemaadminapi.services.ProjectionService;
import com.example.cienemaadminapi.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class ProjectionController {

    @Autowired
    private ProjectionService projectionService;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/projections/add")
    public String showAddForm(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "addProjection";
    }

    @PostMapping("/projections/add")
    public String handleAddProjection(
            @RequestParam Long movieId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime time,
            @RequestParam Integer hall,
            RedirectAttributes redirectAttributes) {

        try {
            projectionService.addProjectionWithMovie(movieId, date, time, hall);
            return "redirect:/admin/projections/movie.title/asc/0";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Nie znaleziono filmu o podanym ID");
            return "redirect:/admin/projections/add";
        }
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


    //page pagination
    @GetMapping("/projections/{field}/{direction}/{offset}")
    public String getProjectionsWithPaginationAndSorting(@PathVariable String field,
                                                         @PathVariable String direction,
                                                         @PathVariable int offset,
                                                         @RequestParam(name = "roomFilter", required = false) Integer roomFilter,
                                                         Model model) {
        Page<Projection> page = projectionService.findFilteredProjections(offset, field, direction, roomFilter);

        Set<Integer> roomNumbers = projectionService.getDistinctRoomNumbers();

        model.addAttribute("projections", page.getContent());
        model.addAttribute("roomNumbers", roomNumbers);
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("sortField", field);
        model.addAttribute("sortDirection", direction);
        model.addAttribute("reverseSortDirection", direction.equals("asc") ? "desc" : "asc");
        model.addAttribute("roomFilter", roomFilter);

        return "projections";
    }
}

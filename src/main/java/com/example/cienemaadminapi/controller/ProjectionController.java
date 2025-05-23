package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.services.ProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

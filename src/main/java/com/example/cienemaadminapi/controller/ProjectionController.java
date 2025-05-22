package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.services.ProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}

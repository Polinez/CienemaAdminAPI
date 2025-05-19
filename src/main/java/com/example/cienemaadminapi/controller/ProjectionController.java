package com.example.cienemaadminapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProjectionController {

    @GetMapping("/projections")
    public String projections() {
        return "projections";
    }
}

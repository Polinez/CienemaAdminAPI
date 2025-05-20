package com.example.cienemaadminapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}

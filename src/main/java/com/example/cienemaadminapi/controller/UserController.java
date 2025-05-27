package com.example.cienemaadminapi.controller;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.User;
import com.example.cienemaadminapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    //page pagination
    @GetMapping("/users/{field}/{direction}/{offset}")
    public String getUsersWithPaginationAndSorting(@PathVariable String field,
                                                   @PathVariable String direction,
                                                   @PathVariable int offset,
                                                   Model model) {
        Page<User> usersPage = userService.findUsersWithPaginationAndSorting(offset, field, direction);

        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("currentPage", offset);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("sortField", field);
        model.addAttribute("sortDirection", direction);
        model.addAttribute("reverseSortDirection", direction.equalsIgnoreCase("asc") ? "desc" : "asc");

        return "users";
    }


}
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

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/{field}")
    public String getUsersWithSorting(@PathVariable String field, Model model) {
        List<User> users = userService.findUsersWithSorting(field);
        model.addAttribute("users", users);
        return "users";
    }

    //page pagination
    @GetMapping("/users/pagination/{offset}/{pageSize}")
    public String getUsersWithPagination(@PathVariable int offset, @PathVariable int pageSize, Model model) {
        Page<User> usersWithPagination = userService.findUsersWithPagination(offset, pageSize);
        model.addAttribute("users", usersWithPagination);
        return "users";
    }

    //page pagination and sorting at the same time
    @GetMapping("/users/paginationAndSort/{offset}/{pageSize}/{field}")
    public String getUsersWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field, Model model) {
        Page<User> usersWithPaginationAndSorting = userService.findUsersWithPaginationAndSorting(offset, pageSize, field);
        model.addAttribute("users", usersWithPaginationAndSorting);
        return "users";
    }
}

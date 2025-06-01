package com.example.cienemaadminapi.services;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.User;
import com.example.cienemaadminapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> findUsersWithPaginationAndSorting(int offset, String field, String direction){
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(Sort.Order.desc(field).ignoreCase())
                : Sort.by(Sort.Order.asc(field).ignoreCase());
        Page<User> users = userRepository.findAll(PageRequest.of(offset, 5, sort));
        return users;
    }
}

package com.example.cienemaadminapi.services;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.User;
import com.example.cienemaadminapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<User> findUsersWithSorting(String field){
        return userRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }
}

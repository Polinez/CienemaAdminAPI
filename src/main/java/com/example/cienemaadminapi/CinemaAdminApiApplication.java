package com.example.cienemaadminapi;

import com.example.cienemaadminapi.model.User;
import com.example.cienemaadminapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemaAdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaAdminApiApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Bean
    @Transactional
    public CommandLineRunner run() {
        return args -> {
            insertUser(userRepository);
        };
    }

    public void insertUser(UserRepository userRepository) {
        userRepository.save(new User("admin", "John", "Black", "admin", "admin"));
        userRepository.save(new User("user", "Mark", "Red", "user", "user"));
        userRepository.save(new User("user2", "Anthony", "White", "user2", "user2"));
    }
}

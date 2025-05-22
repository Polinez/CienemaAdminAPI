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

    @Bean
    @Transactional
    public CommandLineRunner run(UserRepository userRepository) {
        return args -> {
            insertUser(userRepository);
        };
    }

    public void insertUser(UserRepository userRepository) {
        userRepository.save(new User("admin", "Adam", "Black", "admin@o2.pl", "admin"));
        userRepository.save(new User("user", "Maria", "Red", "user@o2.pl", "user"));
        userRepository.save(new User("user2", "John", "White", "user2@o2.pl", "user2"));
    }

}

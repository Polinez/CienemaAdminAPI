package com.example.cienemaadminapi;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.model.User;
import com.example.cienemaadminapi.repository.MovieRepository;
import com.example.cienemaadminapi.repository.ProjectionRepository;
import com.example.cienemaadminapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;

@SpringBootApplication
public class CinemaAdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaAdminApiApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ProjectionRepository projectionRepository;

    @Bean
    @Transactional
    public CommandLineRunner run() {
        return args -> {
            insertUser(userRepository);
            //Movie starWars = insertMovie(movieRepository);
            Movie starWars = new Movie("star wars", "abcabc", "Tarantino", 120);
            starWars = movieRepository.save(starWars);
            movieRepository.flush();
            insertProjection(projectionRepository, starWars);
        };
    }

    public void insertUser(UserRepository userRepository) {
        userRepository.save(new User("admin", "John", "Black", "admin", "admin"));
        userRepository.save(new User("user", "Mark", "Red", "user", "user"));
        userRepository.save(new User("user2", "Anthony", "White", "user2", "user2"));
    }

    public Movie insertMovie(MovieRepository movieRepository) {
        Movie starWars = new Movie("star wars", "abcabc", "Tarantino", 120);
        starWars = movieRepository.save(starWars);
        Movie harryPotter = new Movie("harry potter", "abcabc", "rowling", 90);
        movieRepository.save(harryPotter);

        return starWars;
    }

    public void insertProjection(ProjectionRepository projectionRepository, Movie starWars) {
        if (starWars.getId() == null) {
            throw new RuntimeException("Movie id is null - movie not saved correctly");
        }
        projectionRepository.save(new Projection(starWars, Date.valueOf("2025-07-15"), Time.valueOf("17:00:00"), 3));
    }


}

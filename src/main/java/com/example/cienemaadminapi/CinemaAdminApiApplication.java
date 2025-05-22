package com.example.cienemaadminapi;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.model.Reservation;
import com.example.cienemaadminapi.model.User;
import com.example.cienemaadminapi.repository.MovieRepository;
import com.example.cienemaadminapi.repository.ProjectionRepository;
import com.example.cienemaadminapi.repository.ReservationRepository;
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
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private ReservationRepository reservationRepository;

    @Bean
    @Transactional
    public CommandLineRunner run() {
        return args -> {
            User user1 = insertUser(userRepository);
            Movie starWars = new Movie("star wars", "abcabc", "Tarantino", 120);
            starWars = movieRepository.save(starWars);
            movieRepository.flush();
            Projection projection = insertProjection(projectionRepository, starWars);
            List<Integer> seats = new ArrayList<Integer>();
            seats.add(15);
            seats.add(16);
            insertReservation(reservationRepository, user1, projection, seats, 32.00);
        };
    }

    public User insertUser(UserRepository userRepository) {
        User user1 = userRepository.save(new User("admin", "John", "Black", "admin", "admin"));
        userRepository.save(new User("user", "Mark", "Red", "user", "user"));
        userRepository.save(new User("user2", "Anthony", "White", "user2", "user2"));
        return user1;
    }

    public Projection insertProjection(ProjectionRepository projectionRepository, Movie starWars) {
        Projection projection = projectionRepository.save(new Projection(starWars, Date.valueOf("2025-07-15"), Time.valueOf("17:00:00"), 3));
        return projection;
    }

    public void insertReservation(ReservationRepository reservationRepository, User user, Projection projection, List<Integer> seats, double price) {
        reservationRepository.save(new Reservation(user, projection, seats, price));
    }
}

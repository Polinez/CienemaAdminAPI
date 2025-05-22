package com.example.cienemaadminapi.data;

import com.example.cienemaadminapi.repository.MovieRepository;
import com.example.cienemaadminapi.repository.ProjectionRepository;
import com.example.cienemaadminapi.repository.ReservationRepository;
import com.example.cienemaadminapi.repository.UserRepository;
import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.model.Reservation;
import com.example.cienemaadminapi.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Component
public class SampleDataRunner implements CommandLineRunner{

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final ProjectionRepository projectionRepository;
    private final ReservationRepository reservationRepository;

    public SampleDataRunner(MovieRepository movieRepository,
                            UserRepository userRepository,
                            ProjectionRepository projectionRepository,
                            ReservationRepository reservationRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.projectionRepository = projectionRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        projectionRepository.deleteAll();
        reservationRepository.deleteAll();
        movieRepository.deleteAll();
        // Add sample data here if needed

        User user1 = insertUser(userRepository);
        Movie starWars = new Movie("star wars", "abcabc", "Tarantino", 120);
        starWars = movieRepository.save(starWars);
        movieRepository.flush();
        Projection projection = insertProjection(projectionRepository, starWars);
        List<Integer> seats = new ArrayList<Integer>();
        seats.add(15);
        seats.add(16);
        insertReservation(reservationRepository, user1, projection, seats, 32.00);
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

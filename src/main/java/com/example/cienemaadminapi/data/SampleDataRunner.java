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

        User user1 = userRepository.save(new User("admin", "John", "Black", "admin", "admin"));
        User user2 = userRepository.save(new User("user", "Mark", "Red", "user", "user"));
        User user3 = userRepository.save(new User("user2", "Anthony", "White", "user2", "user2"));

        Movie movie1 = movieRepository.save(new Movie("Star Wars", "abcabc", "Tarantino", 120));
        Movie movie2 = movieRepository.save(new Movie("Harry Potter", "abcabc", "Python", 90));
        Movie movie3 = movieRepository.save(new Movie("Forrest Gump", "abcabc", "Allen", 70));

        Projection projection1 = projectionRepository.save(new Projection(movie1, Date.valueOf("2025-07-15"), Time.valueOf("17:00:00"), 3));
        Projection projection2 = projectionRepository.save(new Projection(movie2, Date.valueOf("2025-05-28"), Time.valueOf("21:00:00"), 1));
        Projection projection3 = projectionRepository.save(new Projection(movie3, Date.valueOf("2025-06-05"), Time.valueOf("18:00:00"), 2));

        List<Integer> seats = new ArrayList<Integer>();
        seats.add(15);
        seats.add(16);

        reservationRepository.save(new Reservation(user1, projection1, seats, 32.00));
        reservationRepository.save(new Reservation(user1, projection2, seats, 19.00));
        reservationRepository.save(new Reservation(user2, projection2, seats, 19.00));
        reservationRepository.save(new Reservation(user3, projection3, seats, 26.00));
        reservationRepository.save(new Reservation(user2, projection1, seats, 32.00));
        reservationRepository.save(new Reservation(user3, projection2, seats, 19.00));
    }
}

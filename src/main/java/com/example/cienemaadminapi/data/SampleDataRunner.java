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

@Component
public class SampleDataRunner {

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

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        movieRepository.deleteAll();
        userRepository.deleteAll();
        projectionRepository.deleteAll();
        reservationRepository.deleteAll();

        // Add sample data here if needed
    }
}

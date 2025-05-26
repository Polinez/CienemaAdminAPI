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

        User user1 = userRepository.save(new User("admin", "John", "Black", "admin@admin.pl", "admin"));
        User user2 = userRepository.save(new User("user", "Mark", "Red", "user@user.pl", "user"));
        User user3 = userRepository.save(new User("user2", "Anthony", "White", "user2@user2.pl", "user2"));

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

        // Generowanie użytkowników
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            users.add(new User("user" + i, "Name" + i, "Surname" + i, "user" + i + "@example.com", "password" + i));
        }
        userRepository.saveAll(users);

        // Generowanie filmów
        List<Movie> movies = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            movies.add(new Movie("Movie" + i, "Description" + i, "Director" + i, 90 + i % 30));
        }
        movieRepository.saveAll(movies);

        // Generowanie projekcji
        List<Projection> projections = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Movie movie = movies.get(i % movies.size());
            projections.add(new Projection(movie, Date.valueOf("2025-07-" + ((i % 30) + 1)),
                    Time.valueOf((12 + i % 12) + ":00:00"), i % 5 + 1));
        }
        projectionRepository.saveAll(projections);

        // Generowanie rezerwacji
        List<Reservation> reservations = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            User user = users.get(i % users.size());
            Projection projection = projections.get(i % projections.size());
            List<Integer> seats2 = List.of((i % 50) + 1, (i % 50) + 2); // Losowe miejsca
            reservations.add(new Reservation(user, projection, seats2, 20.00 + (i % 10)));
        }
        reservationRepository.saveAll(reservations);
    }
}

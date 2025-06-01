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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Component
public class SampleDataRunner implements CommandLineRunner{
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private ReservationRepository reservationRepository;

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
        Movie movie4 = movieRepository.save(new Movie("A Very Long Movie Title That Exceeds Normal LengthA Very Long Movie Title That Exceeds Normal LengthA Very Long Movie Title That Exceeds Normal Length",
            "A Very Long Movie Title That Exceeds Normal LengthA Very Long Movie Title That Exceeds Normal LengthA Very Long Movie Title That Exceeds Normal Length",
            "A Very Long Movie Title That Exceeds Normal LengthA Very Long Movie Title That Exceeds Normal LengthA Very Long Movie Title That Exceeds Normal Length",
            150));
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

        // Generowanie przykładowych danych

        // Generowanie użytkowników
        List<User> users = new ArrayList<>();
        String[] firstNames = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Hannah", "Ivy", "Jack"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
        for (String firstName : firstNames) {
            for (String lastName : lastNames) {
                users.add(new User(
                        "user_" + firstName.toLowerCase() + "_" + lastName.toLowerCase(),
                        firstName,
                        lastName,
                        firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com",
                        "password_" + firstName.toLowerCase()
                ));
            }
        }
        userRepository.saveAll(users);

        // Generowanie filmów
        List<Movie> movies = new ArrayList<>();
        String[] movieTitles = {"Eternal Dawn", "Shadow's Whisper", "Echoes of Silence", "Crystal Horizons", "Iron Tempest"};
        String[] directors = {"Quentin Tarantino", "Christopher Nolan", "Steven Spielberg", "Greta Gerwig", "Sofia Coppola"};
        String[] descriptions = {"A tale of love and betrayal.", "A thrilling journey through the unknown.", "A story of hope and courage."};
        for (String title : movieTitles) {
            for (String director : directors) {
                movies.add(new Movie(
                        title + " - " + director.split(" ")[0],
                        descriptions[directors.length % descriptions.length],
                        director,
                        100
                ));
            }
        }
        movieRepository.saveAll(movies);

        // Generowanie projekcji
        List<Projection> projections = new ArrayList<>();
        String[] times = {"12:00:00", "15:00:00", "18:00:00", "20:00:00"};
        for (String time : times) {
            for (Movie movie : movies) {
                projections.add(new Projection(
                        movie,
                        Date.valueOf("2025-07-15"),
                        Time.valueOf(time),
                        3
                ));
            }
        }
        projectionRepository.saveAll(projections);

        // Generowanie rezerwacji
        List<Reservation> reservations = new ArrayList<>();
        for (User user : users) {
            for (Projection projection : projections) {
                seats = List.of(1, 2); // Przykładowe miejsca
                reservations.add(new Reservation(
                        user,
                        projection,
                        seats,
                        25.00
                ));
            }
        }
        reservationRepository.saveAll(reservations);
    }
}

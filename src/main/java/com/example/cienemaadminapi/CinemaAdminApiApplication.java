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
}

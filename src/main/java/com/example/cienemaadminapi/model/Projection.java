package com.example.cienemaadminapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter
public class Projection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    private Date date;
    private Time startTime;

    //can be added as object type "room"
    private int roomNumber;

    public Projection() {
    }

    public Projection(Movie movie, Date date, Time startTime, int roomNumber) {
        this.movie = movie;
        this.date = date;
        this.startTime = startTime;
        this.roomNumber = roomNumber;
    }
}

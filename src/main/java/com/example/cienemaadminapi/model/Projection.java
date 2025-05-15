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
    private Long seans_id;
    @ManyToOne
    @JoinColumn(name = "movie_movie_id")
    private Movie movie;
    private Date date;
    private Time startTime;

    //can be added as object type "room"
    private int roomNumber;
}

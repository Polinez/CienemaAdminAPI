package com.example.cienemaadminapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movie_id;

    private String title;
    private String description;
    private String director;
    private String actor;
    private String year;

    @ManyToOne
    @JoinColumn(name = "projection_date_seans_id")
    private Projection projection;
}

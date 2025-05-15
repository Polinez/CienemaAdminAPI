package com.example.cienemaadminapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;
    private String description;
    private String director;
    private String actor;
    private String year;

    @OneToMany
    //@JoinColumn(name = "projection_date_seans_id")
    private List<Projection> projection;
}

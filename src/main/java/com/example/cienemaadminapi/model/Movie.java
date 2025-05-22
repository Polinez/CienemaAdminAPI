package com.example.cienemaadminapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String director;
    private int duration;

    @OneToMany
    @JoinColumn(name = "projectionId")
    private List<Projection> projection;

    public Movie() {
    }

    public Movie(String title, String description, String director, int duration) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.duration = duration;
    }
}

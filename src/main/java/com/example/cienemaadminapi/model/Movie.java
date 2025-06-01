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
    @Column(name = "movie_id")
    private Long id;

    private String title;

    @Lob
    private String description;
    private String director;
    private int duration;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Projection> projections;

    public Movie() {
    }

    public Movie(String title, String description, String director, int duration) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.duration = duration;
    }
}

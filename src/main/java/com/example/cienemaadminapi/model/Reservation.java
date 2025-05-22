package com.example.cienemaadminapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "projectionId")
    private Projection projection;

    @ElementCollection
    private List<Integer> seatsId;

    public Reservation() {
    }

    public Reservation(User user, Projection projection, List<Integer> seatsId) {
        this.user = user;
        this.projection = projection;
        this.seatsId = seatsId;
    }
}

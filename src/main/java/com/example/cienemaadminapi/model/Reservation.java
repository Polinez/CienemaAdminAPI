package com.example.cienemaadminapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "projection_id")
    private Projection projection;

    @ElementCollection
    private List<Integer> seatsId;

    private double price;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Reservation() {
    }

    public Reservation(User user, Projection projection, List<Integer> seatsId, double price) {
        this.user = user;
        this.projection = projection;
        this.seatsId = seatsId;
        this.price = price;
    }
}

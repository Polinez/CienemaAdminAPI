package com.example.cienemaadminapi.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String firstName;
    private String surname;
    private String email;
    private String password;

    @OneToMany
    private List<Reservation> reservations;
}

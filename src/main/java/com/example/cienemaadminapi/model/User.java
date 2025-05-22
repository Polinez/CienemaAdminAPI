package com.example.cienemaadminapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "app_users")
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

    public User(String username, String firstName, String surname, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}

package com.example.cienemaadminapi.repository;

import com.example.cienemaadminapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaAdminAPIReservationRepository extends JpaRepository<Reservation,Long> {
}

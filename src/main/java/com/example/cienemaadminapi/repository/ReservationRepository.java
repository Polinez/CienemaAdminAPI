package com.example.cienemaadminapi.repository;

import com.example.cienemaadminapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByProjectionId(Long projectionId);
}

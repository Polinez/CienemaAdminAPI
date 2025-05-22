package com.example.cienemaadminapi.services;


import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.model.Reservation;
import com.example.cienemaadminapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByProjectionId(Long projectionId) {
        return reservationRepository.findByProjectionId(projectionId);
    }
}

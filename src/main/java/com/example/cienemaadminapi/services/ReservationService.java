package com.example.cienemaadminapi.services;


import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.model.Reservation;
import com.example.cienemaadminapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rezerwacja o podanym ID nie istnieje: " + id));
    }

    public List<Reservation> getReservationsByProjectionId(Long projectionId) {
        return reservationRepository.findByProjectionId(projectionId);
    }

    //reservation count and revenue statistics
    public List<Reservation> getReservationsForDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime startOfNextDay = date.plusDays(1).atStartOfDay();

        return reservationRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThan(startOfDay, startOfNextDay);
    }


    public Page<Reservation> findReservationsWithPaginationAndSorting(Long projectionId, int offset, String field, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(field).ascending() : Sort.by(field).descending();
        PageRequest pageRequest = PageRequest.of(offset, 10, sort);
        return reservationRepository.findByProjectionId(projectionId, pageRequest);
    }

}

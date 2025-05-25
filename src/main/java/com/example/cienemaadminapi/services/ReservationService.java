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

    //reservation count and revenue statistics
    public List<Reservation> getReservationsForDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime startOfNextDay = date.plusDays(1).atStartOfDay();

        return reservationRepository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThan(startOfDay, startOfNextDay);
    }

    public List<Reservation> findReservationWithSorting(String field){
        return reservationRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Reservation> findReservationsWithPagination(int offset, int pageSize){
        Page<Reservation> reservations = reservationRepository.findAll(PageRequest.of(offset, pageSize));
        return reservations;
    }

    public Page<Reservation> findReservationsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Reservation> reservations = reservationRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return reservations;
    }
}

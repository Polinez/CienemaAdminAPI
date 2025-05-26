package com.example.cienemaadminapi.repository;

import com.example.cienemaadminapi.model.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.cienemaadminapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection,Long> {
    List<Projection> findByDate(Date date);
    Page<Projection> findByRoomNumber(int roomNumber, Pageable pageable);
}

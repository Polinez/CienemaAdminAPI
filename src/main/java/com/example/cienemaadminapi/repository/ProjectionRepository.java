package com.example.cienemaadminapi.repository;

import com.example.cienemaadminapi.model.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection,Long> {
}

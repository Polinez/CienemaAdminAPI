package com.example.cienemaadminapi.repository;

import com.example.cienemaadminapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaAdminAPIUserRepository extends JpaRepository<User,Long> {
}

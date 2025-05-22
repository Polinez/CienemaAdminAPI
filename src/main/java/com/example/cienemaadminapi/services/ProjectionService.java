package com.example.cienemaadminapi.services;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.repository.ProjectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectionService {
    @Autowired
    private ProjectionRepository projectionRepository;

    public List<Projection> getAllProjections() {
        return projectionRepository.findAll();
    }

    public Optional<Projection> getProjectionById(Long id) {
        return projectionRepository.findById(id);
    }

    public Projection addProjection(Projection projection) {
        return projectionRepository.save(projection);
    }

    public void deleteProjectionById(Long id) {
        projectionRepository.deleteById(id);
    }
}

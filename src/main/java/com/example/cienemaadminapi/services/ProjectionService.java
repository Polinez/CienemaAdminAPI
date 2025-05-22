package com.example.cienemaadminapi.services;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.repository.ProjectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectionService {

    @Autowired
    private ProjectionRepository projectionRepository;

    public List<Projection> getAllProjections() {
        return projectionRepository.findAll();
    }

}

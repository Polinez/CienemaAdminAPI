package com.example.cienemaadminapi.services;

import com.example.cienemaadminapi.model.Movie;
import com.example.cienemaadminapi.model.Projection;
import com.example.cienemaadminapi.model.Reservation;
import com.example.cienemaadminapi.repository.MovieRepository;
import com.example.cienemaadminapi.repository.ProjectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectionService {
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private MovieRepository movieRepository;

    public List<Projection> getAllProjections() {
        return projectionRepository.findAll();
    }

    public Optional<Projection> getProjectionById(Long id) {
        return projectionRepository.findById(id);
    }

    public Projection addProjection(Projection projection) {
        return projectionRepository.save(projection);
    }

    public Projection addProjectionWithMovie(Long movieId, LocalDate date, LocalTime time, Integer roomNumber) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Film nie istnieje"));

        Projection projection = new Projection();
        projection.setMovie(movie);
        projection.setDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        projection.setStartTime(Time.valueOf(time));
        projection.setRoomNumber(roomNumber);

        return projectionRepository.save(projection);
    }

    public void deleteProjectionById(Long id) {
        projectionRepository.deleteById(id);
    }

    public List<Projection> findProjectionsByLocalDate(LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return projectionRepository.findByDate(date);
    }

    public Set<Integer> getDistinctRoomNumbers() {
        return projectionRepository.findAll().stream()
                .map(Projection::getRoomNumber)
                .collect(Collectors.toSet());
    }

    public Page<Projection> findFilteredProjections(int offset, String field, String direction, Integer roomFilter) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(field).ascending() : Sort.by(field).descending();
        PageRequest pageable = PageRequest.of(offset, 5, sort);

        if (roomFilter != null) {
            return projectionRepository.findByRoomNumber(roomFilter, pageable);
        }
        else {
            return projectionRepository.findAll(pageable);
        }
    }

    public List<Projection> findProjectionWithSorting(String field){
        return projectionRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Projection> findProjectionsWithPagination(int offset, int pageSize){
        Page<Projection> projections = projectionRepository.findAll(PageRequest.of(offset, pageSize));
        return projections;
    }

    public Page<Projection> findProjectionsWithPaginationAndSorting(int offset, String field, String direction){
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(field).descending() : Sort.by(field).ascending();
        Page<Projection> projections = projectionRepository.findAll(PageRequest.of(offset, 5, sort));
        return projections;
    }
}

package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Cinema;
import ra.edu.model.entity.Movie;
import ra.edu.model.request.MovieRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.CinemaService;
import ra.edu.service.MovieService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<Cinema>> createCinema(@Valid @RequestBody Cinema cinema) {
        Cinema saved = cinemaService.createCinema(cinema);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Cinema created successfully!",
                saved,
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/movies")
    public ResponseEntity<ApiDataResponse<List<Movie>>> getMoviesByCinema(@PathVariable Long id) {
        List<Movie> movies = cinemaService.getMoviesByCinema(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Retrieved movies for cinema successfully!",
                movies,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}

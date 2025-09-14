package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Cinema;
import ra.edu.model.entity.Movie;
import ra.edu.model.entity.Showtime;
import ra.edu.model.request.ShowtimeRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.CinemaService;
import ra.edu.service.ShowtimeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/showtimes")
public class ShowtimeController {
    @Autowired
    private ShowtimeService showtimeService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<Showtime>> addShowtime(@Valid @RequestBody ShowtimeRequest request) {
        Showtime showtime = showtimeService.addShowtime(request);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Added showtime successfully!",
                showtime,
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Showtime>>> getShowtimes(@RequestParam Long movieId) {
        List<Showtime> showtimes = showtimeService.getShowtimesByMovie(movieId);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Retrieved showtimes successfully!",
                showtimes,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}


package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Movie;
import ra.edu.model.request.MovieRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.MovieService;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<Movie>> createMovie(@Valid @ModelAttribute MovieRequest movieRequest) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Created movie successfully!",
                movieService.createMovie(movieRequest),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Movie>> updateMovie(
            @PathVariable Long id,
            @Valid @ModelAttribute MovieRequest movieRequest) {

        Movie updatedMovie = movieService.updateMovie(id, movieRequest);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Updated movie successfully!",
                updatedMovie,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Void>> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Deleted movie successfully!",
                null,
                null,
                HttpStatus.NO_CONTENT
        ), HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<Movie>>> getMovies(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Movie> movies = movieService.getAllMovies(search, page, size);

        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                (search != null && !search.isBlank())
                        ? "Search movies successfully!"
                        : "Retrieved movies successfully!",
                movies,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}

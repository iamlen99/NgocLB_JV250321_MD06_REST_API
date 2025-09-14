package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.Movie;
import ra.edu.model.request.MovieRequest;

import java.util.List;

public interface MovieService {
    Movie createMovie(MovieRequest movieRequest);
    Movie findById(Long id);
    Movie updateMovie(Long id, MovieRequest movieRequest);
    void deleteMovie(Long id);
    Page<Movie> getAllMovies(String search, int page, int size);
}

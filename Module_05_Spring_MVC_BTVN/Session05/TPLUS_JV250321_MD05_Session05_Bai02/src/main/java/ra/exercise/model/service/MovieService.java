package ra.exercise.model.service;

import ra.exercise.model.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAll();

    boolean addMovie(Movie movie);

    boolean updateMovie(Movie movie);

    boolean deleteMovie(Long id);

    Optional<Movie> findById(Long id);
}

package ra.exercise.model.dao;

import ra.exercise.model.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDAO {
    List<Movie> findAll();

    boolean add(Movie movie);

    boolean update(Movie movie);

    boolean delete(Long id);

    Optional<Movie> findById(Long id);
}

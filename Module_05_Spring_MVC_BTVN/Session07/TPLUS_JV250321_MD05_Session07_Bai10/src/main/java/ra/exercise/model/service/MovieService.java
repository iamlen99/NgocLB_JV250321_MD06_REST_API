package ra.exercise.model.service;

import org.springframework.web.multipart.MultipartFile;
import ra.exercise.model.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> getAllMovies();

    boolean addMovie(Movie movie);

    boolean updateMovie(Movie movie);

    boolean deleteMovie(Integer id);

    Optional<Movie> getMovie(Integer id);
}

package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.Cinema;
import ra.edu.model.entity.Movie;
import ra.edu.model.request.MovieRequest;

import java.util.List;

public interface CinemaService {
    Cinema createCinema(Cinema cinema);
    List<Movie> getMoviesByCinema(Long cinemaId);
}

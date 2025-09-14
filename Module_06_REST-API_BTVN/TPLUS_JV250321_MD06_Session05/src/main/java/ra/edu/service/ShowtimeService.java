package ra.edu.service;

import ra.edu.model.entity.Cinema;
import ra.edu.model.entity.Movie;
import ra.edu.model.entity.Showtime;
import ra.edu.model.request.ShowtimeRequest;

import java.util.List;

public interface ShowtimeService {
    Showtime addShowtime(ShowtimeRequest  request);

    List<Showtime> getShowtimesByMovie(Long movieId);
}

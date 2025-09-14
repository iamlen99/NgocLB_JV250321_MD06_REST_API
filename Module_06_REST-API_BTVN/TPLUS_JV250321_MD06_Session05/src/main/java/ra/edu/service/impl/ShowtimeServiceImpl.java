package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Cinema;
import ra.edu.model.entity.Movie;
import ra.edu.model.entity.Showtime;
import ra.edu.model.request.ShowtimeRequest;
import ra.edu.repository.CinemaRepository;
import ra.edu.repository.MovieRepository;
import ra.edu.repository.ShowtimeRepository;
import ra.edu.service.ShowtimeService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ShowtimeServiceImpl implements ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CinemaRepository cinemaRepository;

    public Showtime addShowtime(ShowtimeRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NoSuchElementException("Movie not found with id=" + request.getMovieId()));
        Cinema cinema = cinemaRepository.findById(request.getCinemaId())
                .orElseThrow(() -> new NoSuchElementException("Cinema not found with id=" + request.getCinemaId()));

        Showtime showtime = Showtime.builder()
                .movie(movie)
                .cinema(cinema)
                .startTime(request.getStartTime())
                .price(request.getPrice())
                .totalSeats(request.getTotalSeats())
                .seatsBooked(0)
                .build();

        Showtime saved = showtimeRepository.save(showtime);

        log.info("Added showtime for movieId={}, cinemaId={}, startTime={}",
                movie.getId(), cinema.getId(), saved.getStartTime());

        return saved;
    }

    @Override
    public List<Showtime> getShowtimesByMovie(Long movieId) {
        long start = System.currentTimeMillis();

        List<Showtime> showtimes = showtimeRepository.findAllByMovieId(movieId);

        long duration = System.currentTimeMillis() - start;

        log.info("Retrieved {} showtimes for movieId={}, time={}ms",
                showtimes.size(), movieId, duration);

        return showtimes;
    }
}

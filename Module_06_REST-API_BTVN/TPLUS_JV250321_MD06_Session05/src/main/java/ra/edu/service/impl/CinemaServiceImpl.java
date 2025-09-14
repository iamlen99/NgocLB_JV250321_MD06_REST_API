package ra.edu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Cinema;
import ra.edu.model.entity.Movie;
import ra.edu.model.request.MovieRequest;
import ra.edu.repository.CinemaRepository;
import ra.edu.repository.MovieRepository;
import ra.edu.service.CinemaService;
import ra.edu.service.MovieService;
import ra.edu.storage.CloudinaryService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public Cinema createCinema(Cinema cinema) {
        log.debug("Creating cinema - name: {}, location: {}, capacity: {}",
                cinema.getName(), cinema.getLocation(), cinema.getCapacity());
        Cinema saved = cinemaRepository.save(cinema);
        log.info("Added cinema: {} at {}", saved.getName(), java.time.LocalDateTime.now());
        return saved;
    }

    @Override
    public List<Movie> getMoviesByCinema(Long cinemaId) {
        long start = System.currentTimeMillis();

        Cinema cinema = cinemaRepository.findById(cinemaId)
                .orElseThrow(() -> new NoSuchElementException("Cinema with id=" + cinemaId + " not found"));

        List<Movie> movies = List.copyOf(cinema.getMovies());

        log.info("Retrieved {} movies for cinema: {}, time={}ms",
                movies.size(), cinema.getName(), System.currentTimeMillis() - start);

        return movies;
    }
}

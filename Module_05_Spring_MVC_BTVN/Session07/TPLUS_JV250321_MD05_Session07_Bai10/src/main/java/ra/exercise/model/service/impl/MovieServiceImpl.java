package ra.exercise.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exercise.model.entity.Movie;
import ra.exercise.model.repository.MovieRepository;
import ra.exercise.model.service.MovieService;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public boolean addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public boolean updateMovie(Movie movie) {
        return movieRepository.update(movie);
    }

    @Override
    public boolean deleteMovie(Integer id) {
        return movieRepository.delete(id);
    }

    @Override
    public Optional<Movie> getMovie(Integer id) {
        return movieRepository.findById(id);
    }
}

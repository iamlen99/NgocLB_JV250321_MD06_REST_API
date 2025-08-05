package ra.exercise.model.service.impl;

import ra.exercise.model.dao.MovieDAO;
import ra.exercise.model.dao.impl.MovieDAOImpl;
import ra.exercise.model.entity.Movie;
import ra.exercise.model.service.MovieService;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {
    public final MovieDAO movieDAO;

    public MovieServiceImpl() {
        movieDAO = new MovieDAOImpl();
    }

    @Override
    public List<Movie> findAll() {
        return movieDAO.findAll();
    }

    @Override
    public boolean addMovie(Movie movie) {
        return movieDAO.add(movie);
    }

    @Override
    public boolean updateMovie(Movie movie) {
        return movieDAO.update(movie);
    }

    @Override
    public boolean deleteMovie(Long id) {
        return movieDAO.delete(id);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieDAO.findById(id);
    }
}

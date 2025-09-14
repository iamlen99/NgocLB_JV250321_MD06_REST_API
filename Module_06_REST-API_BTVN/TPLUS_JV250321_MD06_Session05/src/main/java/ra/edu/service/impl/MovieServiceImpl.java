package ra.edu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Movie;
import ra.edu.model.request.MovieRequest;
import ra.edu.repository.MovieRepository;
import ra.edu.service.MovieService;
import ra.edu.storage.CloudinaryService;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public Movie createMovie(MovieRequest movieRequest) {
        //Thuc hien log debug
        log.debug("Create movie - {} - {} - {}", movieRequest.getTitle(), movieRequest.getDescription(), movieRequest.getReleaseDate());

        //Thuc hien upload anh vao luu duong danh anh vao truong poster

        String imgUrl = null;
        if (movieRequest.getPosterFile() != null) {
            try {
                imgUrl = cloudinaryService.uploadImage(movieRequest.getPosterFile());
            } catch (Exception e) {
                log.error("Failed to upload poster for movie {}: {}", movieRequest.getTitle(), e.getMessage(), e);
            }
        }

        Movie movie = Movie.builder()
                .title(movieRequest.getTitle())
                .description(movieRequest.getDescription())
                .releaseDate(movieRequest.getReleaseDate())
                .poster(imgUrl)
                .build();

        //Thuc hien log info o day
        Movie savedMovie = movieRepository.save(movie);
        log.info("Create movie - {}", movie.toString());
        return savedMovie;
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Movie with ID " + id + " not found"));
    }

    @Override
    public Movie updateMovie(Long id, MovieRequest movieRequest) {
        Movie existingMovie = findById(id);
        log.info("Before update: {}", existingMovie.toString());

        //Thuc hien log debug
        log.debug("Update movie - {} - {} - {}", movieRequest.getTitle(), movieRequest.getDescription(), movieRequest.getReleaseDate());
        String imgUrl = existingMovie.getPoster();
        if (movieRequest.getPosterFile() != null) {
            try {
                imgUrl = cloudinaryService.uploadImage(movieRequest.getPosterFile());
            } catch (Exception e) {
                log.error("Failed to upload poster for movie {}: {}", movieRequest.getTitle(), e.getMessage(), e);
            }
        }

        existingMovie.setTitle(movieRequest.getTitle());
        existingMovie.setDescription(movieRequest.getDescription());
        existingMovie.setReleaseDate(movieRequest.getReleaseDate());
        existingMovie.setPoster(imgUrl);

        //Thuc hien log info o day
        Movie updatedMovie = movieRepository.save(existingMovie);
        log.info("After update - {}", updatedMovie.toString());
        return updatedMovie;
    }

    @Override
    public void deleteMovie(Long id) {
        Movie movie = findById(id);
        movieRepository.delete(movie);
        log.info("Xóa thành công: {}", movie.toString());
    }

    @Override
    public Page<Movie> getAllMovies(String search, int page, int size) {
        long start = System.currentTimeMillis();
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Movie> result;

        try {
            if (search != null && !search.isBlank()) {
                log.debug("Searching movies with keyword: {}", search);
                result = movieRepository.findAllByTitleContaining(search, pageable);
                log.info("Search movies with keyword=[{}], results=[{}], time=[{}]ms",
                        search, result.getTotalElements(), System.currentTimeMillis() - start);
            } else {
                log.debug("Fetching all movies with pagination page={} size={}", page, size);
                result = movieRepository.findAll(pageable);
                log.info("Retrieved all movies, results=[{}], time=[{}]ms",
                        result.getTotalElements(), System.currentTimeMillis() - start);
            }
        } catch (Exception e) {
            log.error("Failed to retrieve movies: {}", e.getMessage(), e);
            throw e;
        }

        return result;
    }
}

package ra.edu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Cinema;
import ra.edu.model.entity.Movie;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}

package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}

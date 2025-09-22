package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.RoundCriteria;

@Repository
public interface RoundCriteriaRepository extends JpaRepository<RoundCriteria, Long> {
}

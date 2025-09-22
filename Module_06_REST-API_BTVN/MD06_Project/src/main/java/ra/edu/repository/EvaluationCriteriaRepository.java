package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.EvaluationCriteria;
import ra.edu.model.entity.InternshipPhase;

@Repository
public interface EvaluationCriteriaRepository extends JpaRepository<EvaluationCriteria, Long> {
    boolean existsByCriterionName(String criterionName);
}

package ra.edu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.AssessmentRound;

@Repository
public interface AssessmentRoundRepository extends JpaRepository<AssessmentRound, Long> {
    Page<AssessmentRound> findAllByInternshipPhaseId(Long internshipPhaseId, Pageable pageable);
}

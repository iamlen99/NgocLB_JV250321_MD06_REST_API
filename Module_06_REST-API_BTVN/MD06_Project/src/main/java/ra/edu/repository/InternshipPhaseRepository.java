package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.InternshipPhase;

@Repository
public interface InternshipPhaseRepository extends JpaRepository<InternshipPhase, Long> {
    InternshipPhase findByPhaseName(String phaseName);

    boolean existsByPhaseName(String phaseName);
}

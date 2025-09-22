package ra.edu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.AssessmentResult;
import ra.edu.model.entity.AssessmentRound;
import ra.edu.model.entity.EvaluationCriteria;
import ra.edu.model.entity.InternshipAssignment;

@Repository
public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    Page<AssessmentResult> findAllByUsersUserId(Long usersUserId, Pageable pageable);

    @Query("select a from AssessmentResult a where a.assignment.student.studentId = :userId")
    Page<AssessmentResult> findAllByStudentId(Long userId, Pageable pageable);

    boolean existsByAssignmentAndRoundAndCriteria(InternshipAssignment assignment, AssessmentRound round, EvaluationCriteria criteria);

    boolean existsByAssignmentAndRoundAndCriteriaAndResultIdNot(InternshipAssignment assignment, AssessmentRound round, EvaluationCriteria criteria, Long resultId);
}

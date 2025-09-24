package ra.edu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.InternshipAssignment;

import java.util.Optional;

@Repository
public interface InternshipAssignmentRepository extends JpaRepository<InternshipAssignment, Long> {
    Page<InternshipAssignment> findAllByMentorMentorId(Long mentorMentorId, Pageable pageable);

    Page<InternshipAssignment> findAllByStudentStudentId(Long studentStudentId, Pageable pageable);

    Optional<InternshipAssignment> findByAssignmentIdAndMentorMentorId(Long assignmentId, Long mentorMentorId);

    Optional<InternshipAssignment> findByAssignmentIdAndStudentStudentId(Long assignmentId, Long studentStudentId);
}

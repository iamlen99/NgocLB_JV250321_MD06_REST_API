package ra.edu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Mentor;
import ra.edu.model.entity.Student;
import ra.edu.model.response.MentorDetailsResponse;
import ra.edu.model.response.MentorResponse;

import java.util.Optional;


@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    @Query("select u.mentor.mentorId, u.fullName, u.mentor.department, u.mentor.academicRank from Users u")
    Page<MentorResponse> findAllMentors(Pageable pageable);

    @Query("""
            select u.mentor.mentorId, u.fullName, u.email, u.phone, u.mentor.department, u.mentor.academicRank, 
                        u.mentor.createdAt, u.mentor.updatedAt 
            from Users u
            """)
    Page<MentorDetailsResponse> findAllMentorsDetails(Pageable pageable);

    @Query("""
            select u.mentor.mentorId, u.fullName, u.email, u.phone, u.mentor.department, u.mentor.academicRank,
                        u.mentor.createdAt, u.mentor.updatedAt
            from Users u where u.mentor.mentorId = :mentorId
            """)
    Optional<MentorDetailsResponse> findMentorsDetailsByMentorId(Long mentorId);
}

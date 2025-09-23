package ra.edu.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Student;
import ra.edu.model.response.StudentResponse;
import ra.edu.model.response.StudentsDetailsResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByStudentCode(String studentCode);

    Student findByStudentCode(String studentCode);

    @Query("""
            select u.student.studentId, u.fullName, u.email, u.phone, u.student.studentCode, u.student.major,
            u.student.studentClass, u.student.dateOfBirth, u.student.address, u.student.createdAt, u.student.updatedAt
            from Users u
            """)
    Page<StudentResponse> findAllMentor(Pageable pageable);

    @Query("""
            select u.student.studentId, u.fullName, u.student.studentCode, u.student.major, u.student.studentClass,
            u.student.dateOfBirth, u.student.address
            from Users u
            where u.mentor.mentorId = :userId
            """)
    Page<StudentResponse> findAllByMentorId(Long userId, Pageable pageable);

    @Query("""
           select u.student.studentId, u.fullName, u.student.studentCode, u.student.major, u.student.studentClass,
           u.student.dateOfBirth, u.student.address
           from Users u
           where u.student.studentId = :studentId
           """)
    Optional<StudentsDetailsResponse> findByStudentId(Long studentId);

    @Query("""
           select u.student.studentId, u.fullName, u.student.studentCode, u.student.major, u.student.studentClass,
           u.student.dateOfBirth, u.student.address
           from Users u
           where u.student.studentId = :studentId
           and u.mentor.mentorId = :mentorId
           """)
    Optional<StudentsDetailsResponse> findByStudentIdAndMentorId(Long studentId, Long mentorId);
}

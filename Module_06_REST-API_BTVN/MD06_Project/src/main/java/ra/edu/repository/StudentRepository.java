package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByStudentCode(String studentCode);

    Student findByStudentCode(String studentCode);
}

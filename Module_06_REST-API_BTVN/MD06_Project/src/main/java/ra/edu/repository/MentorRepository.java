package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Mentor;
import ra.edu.model.entity.Student;


@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
}

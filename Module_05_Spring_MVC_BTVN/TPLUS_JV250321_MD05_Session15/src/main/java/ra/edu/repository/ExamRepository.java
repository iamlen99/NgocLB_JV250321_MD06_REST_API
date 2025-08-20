package ra.edu.repository;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Exam;

import java.awt.print.Pageable;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}

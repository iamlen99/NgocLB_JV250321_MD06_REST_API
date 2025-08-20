package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Exam;
import ra.edu.model.entity.History;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}

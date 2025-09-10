package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Author;
import ra.edu.model.entity.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}

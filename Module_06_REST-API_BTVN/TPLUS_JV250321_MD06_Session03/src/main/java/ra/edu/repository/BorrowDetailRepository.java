package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Borrow;
import ra.edu.model.entity.BorrowDetail;

import java.util.List;

@Repository
public interface BorrowDetailRepository extends JpaRepository<BorrowDetail, Long> {
    List<BorrowDetail> findAllByBorrowId(Long borrowId);
}

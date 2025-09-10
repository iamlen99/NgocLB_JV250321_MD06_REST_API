package ra.edu.service;

import ra.edu.model.entity.Borrow;

import java.util.List;

public interface BorrowService {
    List<Borrow> getAllBorrows();

    Borrow createBorrow(Borrow borrow, Long memberId);

    Borrow findBorrowById(Long borrowId);
}

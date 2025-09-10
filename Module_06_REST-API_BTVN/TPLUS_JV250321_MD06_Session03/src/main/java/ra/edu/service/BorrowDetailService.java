package ra.edu.service;

import ra.edu.model.entity.BorrowDetail;

import java.util.List;

public interface BorrowDetailService {
    List<BorrowDetail> getBorrowDetailsByBorrowId(Long borrowId);

    BorrowDetail createBorrowDetail(BorrowDetail borrowDetail, Long borrowId, Long bookId, Integer quantity);
}

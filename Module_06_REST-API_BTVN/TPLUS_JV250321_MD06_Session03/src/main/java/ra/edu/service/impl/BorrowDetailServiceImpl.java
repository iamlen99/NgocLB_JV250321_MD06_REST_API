package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Book;
import ra.edu.model.entity.Borrow;
import ra.edu.model.entity.BorrowDetail;
import ra.edu.repository.BorrowDetailRepository;
import ra.edu.service.BookService;
import ra.edu.service.BorrowDetailService;
import ra.edu.service.BorrowService;

import java.util.List;

@Service
public class BorrowDetailServiceImpl implements BorrowDetailService {
    @Autowired
    private BorrowDetailRepository borrowDetailRepository;
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private BookService bookService;

    @Override
    public List<BorrowDetail> getBorrowDetailsByBorrowId(Long borrowId) {
        return borrowDetailRepository.findAllByBorrowId(borrowId);
    }

    @Override
    public BorrowDetail createBorrowDetail(BorrowDetail borrowDetail, Long borrowId, Long bookId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        Borrow borrow = borrowService.findBorrowById(borrowId);
        Book book = bookService.getBookById(bookId);

        borrowDetail.setBorrow(borrow);
        borrowDetail.setBook(book);
        borrowDetail.setQuantity(quantity);
        return borrowDetailRepository.save(borrowDetail);
    }
}

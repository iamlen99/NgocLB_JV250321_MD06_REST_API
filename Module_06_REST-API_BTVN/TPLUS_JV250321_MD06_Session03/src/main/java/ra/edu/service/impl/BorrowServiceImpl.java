package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Borrow;
import ra.edu.model.entity.Member;
import ra.edu.repository.BorrowRepository;
import ra.edu.service.BorrowService;
import ra.edu.service.MemberService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private MemberService memberService;

    @Override
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    @Override
    public Borrow createBorrow(Borrow borrow, Long memberId) {
        Member member = memberService.getMemberById(memberId);
        borrow.setMember(member);
        return borrowRepository.save(borrow);
    }

    @Override
    public Borrow findBorrowById(Long borrowId) {
        return borrowRepository.findById(borrowId)
                .orElseThrow(() -> new NoSuchElementException("Borrow not found by id: " + borrowId));
    }
}

package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.BorrowDetail;
import ra.edu.service.BorrowDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrow-details")
public class BorrowDetailController {
    @Autowired
    private BorrowDetailService borrowDetailService;

    @GetMapping("/by-borrow")
    public ResponseEntity<ApiDataResponse<List<BorrowDetail>>> getAllBorrowDetails(@RequestParam Long borrowId) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list BorrowDetails successfully",
                borrowDetailService.getBorrowDetailsByBorrowId(borrowId),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<BorrowDetail>> insertBorrowDetail(@RequestBody BorrowDetail borrowDetail) {
        BorrowDetail newBorrowDetail = borrowDetailService.createBorrowDetail(borrowDetail,
                borrowDetail.getBorrow().getId(),
                borrowDetail.getBook().getId(),
                borrowDetail.getQuantity());
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Add new borrowDetail successfully",
                newBorrowDetail,
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }
}

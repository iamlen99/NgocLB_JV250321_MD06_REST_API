package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.dto.response.ApiDataResponse;
import ra.edu.model.entity.Borrow;
import ra.edu.service.BorrowService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borrows")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Borrow>>> getAllBorrows() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Get list Borrows successfully",
                borrowService.getAllBorrows(),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Borrow>> insertBorrow(@RequestBody Borrow borrow) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Add new borrow successfully",
                borrowService.createBorrow(borrow, borrow.getMember().getId()),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }
}

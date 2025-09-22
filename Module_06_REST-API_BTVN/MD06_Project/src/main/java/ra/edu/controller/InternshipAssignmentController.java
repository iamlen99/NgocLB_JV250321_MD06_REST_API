package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.InternshipAssignment;
import ra.edu.model.request.InternshipAssignmentRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.InternshipAssignmentService;

@RestController
@RequestMapping("/api/v1/internship_assignments")
@RequiredArgsConstructor
public class InternshipAssignmentController {
    private final InternshipAssignmentService internshipAssignmentService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<InternshipAssignment>>> getAllInternshipAssignments(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<InternshipAssignment> evaluationCriterion = internshipAssignmentService.getAllInternshipAssignment(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(evaluationCriterion, "Lấy danh sách phân công thực tập thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<InternshipAssignment>> getInternshipAssignmentById(
            @PathVariable Long id
    ) {
        InternshipAssignment internshipAssignment = internshipAssignmentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(internshipAssignment, "Lấy chi tiết một phân công thực tập theo ID " + id + " thành công"));
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<InternshipAssignment>> createInternshipAssignment(
            @Valid @RequestBody InternshipAssignmentRequest internshipAssignmentRequest
    ) {
        InternshipAssignment internshipAssignment = internshipAssignmentService.createInternshipAssignment(internshipAssignmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiDataResponse.success(internshipAssignment, "Tạo phân công thực tập mới thành công"));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiDataResponse<InternshipAssignment>> updateStatusInternshipAssignment(
            @RequestParam String status,
            @PathVariable Long id
    ) {
        InternshipAssignment internshipAssignmentUpdate = internshipAssignmentService.updateStatusInternshipAssignment(id, status);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(internshipAssignmentUpdate, "Cập nhật trạng thái của phân công thực tập thành công"));
    }
}

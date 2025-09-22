package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.InternshipPhase;
import ra.edu.model.request.InternshipPhaseRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.InternshipPhaseService;

@RestController
@RequestMapping("/api/v1/internship_phases")
@RequiredArgsConstructor
public class InternshipPhaseController {
    private final InternshipPhaseService internshipPhaseService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<InternshipPhase>> createInternshipPhase(@Valid @RequestBody InternshipPhaseRequest internshipPhaseRequest) {
        InternshipPhase internshipPhase = internshipPhaseService.createInternshipPhase(internshipPhaseRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiDataResponse.success(internshipPhase, "Tạo giai đoạn thực tập mới thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<InternshipPhase>>> getAllInternshipPhases(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<InternshipPhase> internshipPhases = internshipPhaseService.getAllInternshipPhase(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(internshipPhases, "Lấy danh sách giai đoạn thực tập thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<InternshipPhase>> getInternshipPhaseById(
            @PathVariable Long id
    ) {
        InternshipPhase internshipPhase = internshipPhaseService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(internshipPhase, "Lấy giai đoạn thực tập theo id " + id + " thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<InternshipPhase>> updateInternshipPhase(
            @Valid @RequestBody InternshipPhaseRequest internshipPhaseRequest,
            @PathVariable Long id
    ) {
        InternshipPhase internshipPhaseUpdate = internshipPhaseService.updateInternshipPhase(id, internshipPhaseRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(internshipPhaseUpdate, "Cập nhật giai đoạn thực tập thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<InternshipPhase>> deleteInternshipPhase(@PathVariable Long id) {
        InternshipPhase deletedInternshipPhase = internshipPhaseService.findById(id);
        internshipPhaseService.deleteInternshipPhase(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(deletedInternshipPhase, "Xóa giai đoạn thực tập thành công"));
    }
}

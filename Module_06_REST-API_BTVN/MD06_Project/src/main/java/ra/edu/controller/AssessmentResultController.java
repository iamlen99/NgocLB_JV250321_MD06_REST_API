package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.AssessmentResult;
import ra.edu.model.request.AssessmentResultRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.AssessmentResultService;

@RestController
@RequestMapping("/api/v1/assessment_results")
@RequiredArgsConstructor
public class AssessmentResultController {
    private final AssessmentResultService assessmentResultService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<AssessmentResult>>> getAllAssessmentResults(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<AssessmentResult> assessmentResult = assessmentResultService.getAllAssessmentResult(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(assessmentResult, "Lấy danh sách kết quả đánh giá chi tiết thành công"));
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<AssessmentResult>> createAssessmentResult(
            @Valid @RequestBody AssessmentResultRequest assessmentResultRequest
    ) {
        AssessmentResult assessmentResult = assessmentResultService.createAssessmentResult(assessmentResultRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiDataResponse.success(assessmentResult, "Tạo kết quả đánh giá chi tiết thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<AssessmentResult>> updateAssessmentResult(
            @Valid @RequestBody AssessmentResultRequest assessmentResultRequest,
            @PathVariable Long id
    ) {
        AssessmentResult assessmentResultUpdate = assessmentResultService.updateAssessmentResult(id, assessmentResultRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(assessmentResultUpdate, "Cập nhật kết quả đánh giá chi tiết thành công"));
    }
}

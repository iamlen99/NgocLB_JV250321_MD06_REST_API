package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.AssessmentRound;
import ra.edu.model.request.AssessmentRoundRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.AssessmentRoundService;

@RestController
@RequestMapping("/api/v1/assessment_rounds")
@RequiredArgsConstructor
public class AssessmentRoundController {
    private final AssessmentRoundService assessmentRoundService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<AssessmentRound>> createAssessmentRound(
            @Valid @RequestBody AssessmentRoundRequest assessmentRoundRequest
    ) {
        AssessmentRound assessmentRound = assessmentRoundService.createAssessmentRound(assessmentRoundRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiDataResponse.success(assessmentRound, "Tạo đợt đánh giá mới thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<AssessmentRound>>> getAllAssessmentRounds(
            @RequestParam(required = false) Long phaseId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<AssessmentRound> evaluationCriterion = assessmentRoundService.getAllAssessmentRound(page, size,  phaseId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(evaluationCriterion, "Lấy danh sách đợt đánh giá thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<AssessmentRound>> getAssessmentRoundById(
            @PathVariable Long id
    ) {
        AssessmentRound assessmentRound = assessmentRoundService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(assessmentRound, "Lấy đợt đánh giá theo id " + id + " thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<AssessmentRound>> updateAssessmentRound(
            @Valid @RequestBody AssessmentRoundRequest assessmentRoundRequest,
            @PathVariable Long id
    ) {
        AssessmentRound assessmentRoundUpdate = assessmentRoundService.updateAssessmentRound(id, assessmentRoundRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(assessmentRoundUpdate, "Cập nhật đợt đánh giá thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<AssessmentRound>> deleteAssessmentRound(@PathVariable Long id) {
        AssessmentRound deletedAssessmentRound = assessmentRoundService.findById(id);
        assessmentRoundService.deleteAssessmentRound(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(deletedAssessmentRound, "Xóa đợt đánh giá thành công"));
    }
}

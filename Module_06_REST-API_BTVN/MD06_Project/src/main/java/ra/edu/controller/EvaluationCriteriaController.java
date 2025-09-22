package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.EvaluationCriteria;
import ra.edu.model.request.EvaluationCriteriaRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.EvaluationCriteriaService;

@RestController
@RequestMapping("/api/v1/evaluation_criteria")
@RequiredArgsConstructor
public class EvaluationCriteriaController {
    private final EvaluationCriteriaService evaluationCriteriaService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<EvaluationCriteria>> createEvaluationCriteria(
            @Valid @RequestBody EvaluationCriteriaRequest evaluationCriteriaRequest
    ) {
        EvaluationCriteria evaluationCriteria = evaluationCriteriaService.createEvaluationCriteria(evaluationCriteriaRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiDataResponse.success(evaluationCriteria, "Tạo tiêu chí đánh giá mới thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<EvaluationCriteria>>> getAllEvaluationCriterion(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<EvaluationCriteria> evaluationCriterion = evaluationCriteriaService.getAllEvaluationCriteria(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(evaluationCriterion, "Lấy danh sách tiêu chí đánh giá thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<EvaluationCriteria>> getEvaluationCriteriaById(
            @PathVariable Long id
    ) {
        EvaluationCriteria evaluationCriteria = evaluationCriteriaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(evaluationCriteria, "Lấy tiêu chí đánh giá theo id " + id + " thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<EvaluationCriteria>> updateEvaluationCriteria(
            @Valid @RequestBody EvaluationCriteriaRequest evaluationCriteriaRequest,
            @PathVariable Long id
    ) {
        EvaluationCriteria evaluationCriteriaUpdate = evaluationCriteriaService.updateEvaluationCriteria(id, evaluationCriteriaRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(evaluationCriteriaUpdate, "Cập nhật tiêu chí đánh giá thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<EvaluationCriteria>> deleteEvaluationCriteria(@PathVariable Long id) {
        EvaluationCriteria deletedEvaluationCriteria = evaluationCriteriaService.findById(id);
        evaluationCriteriaService.deleteEvaluationCriteria(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(deletedEvaluationCriteria, "Xóa tiêu chí đánh giá thành công"));
    }
}

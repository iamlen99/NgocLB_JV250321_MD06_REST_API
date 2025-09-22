package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.RoundCriteria;
import ra.edu.model.request.RoundCriteriaRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.RoundCriteriaService;

@RestController
@RequestMapping("/api/v1/round_criteria")
@RequiredArgsConstructor
public class RoundCriteriaController {
    private final RoundCriteriaService roundCriteriaService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<RoundCriteria>> createRoundCriteria(
            @Valid @RequestBody RoundCriteriaRequest roundCriteriaRequest
    ) {
        RoundCriteria roundCriteria = roundCriteriaService.createRoundCriteria(roundCriteriaRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiDataResponse.success(roundCriteria, "Thêm tiêu chí trong một đợt đánh giá thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<RoundCriteria>>> getAllRoundCriterias(
            @RequestParam Long phaseId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<RoundCriteria> evaluationCriterion = roundCriteriaService.getAllRoundCriteria(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(evaluationCriterion, "Lấy danh sách các tiêu chí trong đợt đánh giá thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<RoundCriteria>> getRoundCriteriaById(
            @PathVariable Long id
    ) {
        RoundCriteria roundCriteria = roundCriteriaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(roundCriteria, "Lấy chi tiết một tiêu chí trong đợt đánh giá " + id + " thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<RoundCriteria>> updateRoundCriteria(
            @Valid @RequestBody RoundCriteriaRequest roundCriteriaRequest,
            @PathVariable Long id
    ) {
        RoundCriteria roundCriteriaUpdate = roundCriteriaService.updateRoundCriteria(id, roundCriteriaRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(roundCriteriaUpdate, "Cập nhật trọng số của tiêu chí trong đợt đánh giá thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<RoundCriteria>> deleteRoundCriteria(@PathVariable Long id) {
        RoundCriteria deletedRoundCriteria = roundCriteriaService.findById(id);
        roundCriteriaService.deleteRoundCriteria(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(deletedRoundCriteria, "Xóa tiêu chí khỏi đợt đánh giá thành công"));
    }
}

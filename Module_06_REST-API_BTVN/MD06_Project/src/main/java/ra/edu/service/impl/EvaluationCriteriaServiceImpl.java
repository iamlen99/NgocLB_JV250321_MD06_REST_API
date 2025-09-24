package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.edu.controller_advice.custom_exception.ResourceConflictException;
import ra.edu.model.entity.EvaluationCriteria;
import ra.edu.model.request.EvaluationCriteriaRequest;
import ra.edu.repository.EvaluationCriteriaRepository;
import ra.edu.service.EvaluationCriteriaService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EvaluationCriteriaServiceImpl implements EvaluationCriteriaService {
    private final EvaluationCriteriaRepository evaluationCriteriaRepository;

    @Override
    public Page<EvaluationCriteria> getAllEvaluationCriteria(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        return evaluationCriteriaRepository.findAll(pageable);
    }

    @Override
    public EvaluationCriteria findById(Long id) {
        return evaluationCriteriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy tiêu chí đánh giá có id = " + id));
    }

    @Override
    public EvaluationCriteria createEvaluationCriteria(EvaluationCriteriaRequest evaluationCriteriaRequest) {
        if(evaluationCriteriaRepository.existsByCriterionName(evaluationCriteriaRequest.getCriterionName())) {
            throw new ResourceConflictException("Tên tiêu chí đánh giá đã tồn tại!");
        }

        EvaluationCriteria evaluationCriteria = EvaluationCriteria.builder()
                .criterionName(evaluationCriteriaRequest.getCriterionName())
                .description(evaluationCriteriaRequest.getDescription())
                .maxScore(evaluationCriteriaRequest.getMaxScore())
                .createdAt(LocalDateTime.now().withNano(0))
                .updatedAt(LocalDateTime.now().withNano(0))
                .build();
        return evaluationCriteriaRepository.save(evaluationCriteria);
    }

    @Override
    public EvaluationCriteria updateEvaluationCriteria(Long id, EvaluationCriteriaRequest evaluationCriteriaRequest) {
        EvaluationCriteria existingEvaluationCriteria = findById(id);
        if (!existingEvaluationCriteria.getCriterionName().equals(evaluationCriteriaRequest.getCriterionName())
        && evaluationCriteriaRepository.existsByCriterionName(evaluationCriteriaRequest.getCriterionName())) {
            throw new ResourceConflictException("Tên tiêu chí đánh giá đã tồn tại!");
        }
        existingEvaluationCriteria.setCriterionName(evaluationCriteriaRequest.getCriterionName());
        existingEvaluationCriteria.setDescription(evaluationCriteriaRequest.getDescription());
        existingEvaluationCriteria.setMaxScore(evaluationCriteriaRequest.getMaxScore());
        existingEvaluationCriteria.setUpdatedAt(LocalDateTime.now().withNano(0));
        return evaluationCriteriaRepository.save(existingEvaluationCriteria);
    }

    @Override
    public void deleteEvaluationCriteria(Long id) {
        EvaluationCriteria evaluationCriteria = findById(id);
        evaluationCriteriaRepository.delete(evaluationCriteria);
    }
}

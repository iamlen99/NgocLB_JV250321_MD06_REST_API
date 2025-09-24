package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.AssessmentRound;
import ra.edu.model.entity.EvaluationCriteria;
import ra.edu.model.entity.RoundCriteria;
import ra.edu.model.request.RoundCriteriaRequest;
import ra.edu.repository.RoundCriteriaRepository;
import ra.edu.service.AssessmentRoundService;
import ra.edu.service.EvaluationCriteriaService;
import ra.edu.service.RoundCriteriaService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoundCriteriaServiceImpl implements RoundCriteriaService {
    private final RoundCriteriaRepository roundCriteriaRepository;
    private final AssessmentRoundService assessmentRoundService;
    private final EvaluationCriteriaService  evaluationCriteriaService;

    @Override
    public Page<RoundCriteria> getAllRoundCriteria(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        return roundCriteriaRepository.findAll(pageable);
    }

    @Override
    public RoundCriteria findById(Long id) {
        return roundCriteriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy tiêu chí của đợt đánh giá có id = " + id));
    }

    @Override
    public RoundCriteria createRoundCriteria(RoundCriteriaRequest roundCriteriaRequest) {
        AssessmentRound assessmentRound = assessmentRoundService.findById(roundCriteriaRequest.getRoundId());
        EvaluationCriteria evaluationCriteria = evaluationCriteriaService.findById(roundCriteriaRequest.getCriteriaId());

        RoundCriteria roundCriteria = RoundCriteria.builder()
                .weight(roundCriteriaRequest.getWeight())
                .assessmentRound(assessmentRound)
                .evaluationCriteria(evaluationCriteria)
                .createdAt(LocalDateTime.now().withNano(0))
                .updatedAt(LocalDateTime.now().withNano(0))
                .build();
        return roundCriteriaRepository.save(roundCriteria);
    }

    @Override
    public RoundCriteria updateRoundCriteria(Long id, RoundCriteriaRequest roundCriteriaRequest) {
        RoundCriteria existingRoundCriteria = findById(id);
        AssessmentRound assessmentRound = assessmentRoundService.findById(roundCriteriaRequest.getRoundId());
        EvaluationCriteria evaluationCriteria = evaluationCriteriaService.findById(roundCriteriaRequest.getCriteriaId());

        existingRoundCriteria.setWeight(roundCriteriaRequest.getWeight());
        existingRoundCriteria.setAssessmentRound(assessmentRound);
        existingRoundCriteria.setEvaluationCriteria(evaluationCriteria);
        existingRoundCriteria.setUpdatedAt(LocalDateTime.now().withNano(0));
        return roundCriteriaRepository.save(existingRoundCriteria);
    }

    @Override
    public void deleteRoundCriteria(Long id) {
        RoundCriteria roundCriteria = findById(id);
        roundCriteriaRepository.delete(roundCriteria);
    }
}

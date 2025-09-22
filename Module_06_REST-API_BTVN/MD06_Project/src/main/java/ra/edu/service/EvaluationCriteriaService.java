package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.EvaluationCriteria;
import ra.edu.model.request.EvaluationCriteriaRequest;

public interface EvaluationCriteriaService {
    Page<EvaluationCriteria> getAllEvaluationCriteria(int page, int size);

    EvaluationCriteria findById(Long id);

    EvaluationCriteria createEvaluationCriteria(EvaluationCriteriaRequest evaluationCriteriaPhaseRequest);

    EvaluationCriteria updateEvaluationCriteria(Long id, EvaluationCriteriaRequest evaluationCriteriaPhaseRequest);

    void deleteEvaluationCriteria(Long id);
}

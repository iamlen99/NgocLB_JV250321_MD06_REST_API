package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.AssessmentRound;
import ra.edu.model.request.AssessmentRoundRequest;

public interface AssessmentRoundService {
    Page<AssessmentRound> getAllAssessmentRound(int page, int size, Long phaseId);

    AssessmentRound findById(Long id);

    AssessmentRound createAssessmentRound(AssessmentRoundRequest assessmentRoundRequest);

    AssessmentRound updateAssessmentRound(Long id, AssessmentRoundRequest assessmentRoundRequest);

    void deleteAssessmentRound(Long id);
}

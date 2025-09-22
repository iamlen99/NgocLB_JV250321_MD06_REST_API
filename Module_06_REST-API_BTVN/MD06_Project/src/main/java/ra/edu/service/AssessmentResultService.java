package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.AssessmentResult;
import ra.edu.model.request.AssessmentResultRequest;

public interface AssessmentResultService {
    Page<AssessmentResult> getAllAssessmentResult(int page, int size);

    AssessmentResult createAssessmentResult(AssessmentResultRequest assessmentResultRequest);

    AssessmentResult updateAssessmentResult(Long id, AssessmentResultRequest assessmentResultRequest);
}

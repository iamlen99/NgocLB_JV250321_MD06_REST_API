package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.InternshipPhase;
import ra.edu.model.request.InternshipPhaseRequest;

public interface InternshipPhaseService {
    Page<InternshipPhase> getAllInternshipPhase(int page, int size);

    InternshipPhase findById(Long id);

    InternshipPhase createInternshipPhase(InternshipPhaseRequest internshipPhaseRequest);

    InternshipPhase updateInternshipPhase(Long id, InternshipPhaseRequest internshipPhaseRequest);

    void deleteInternshipPhase(Long id);
}

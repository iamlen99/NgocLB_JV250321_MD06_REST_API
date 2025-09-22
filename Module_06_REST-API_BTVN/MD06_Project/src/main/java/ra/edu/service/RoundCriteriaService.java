package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.RoundCriteria;
import ra.edu.model.request.RoundCriteriaRequest;

public interface RoundCriteriaService {
    Page<RoundCriteria> getAllRoundCriteria(int page, int size);

    RoundCriteria findById(Long id);

    RoundCriteria createRoundCriteria(RoundCriteriaRequest roundCriteriaRequest);

    RoundCriteria updateRoundCriteria(Long id, RoundCriteriaRequest roundCriteriaRequest);

    void deleteRoundCriteria(Long id);
}

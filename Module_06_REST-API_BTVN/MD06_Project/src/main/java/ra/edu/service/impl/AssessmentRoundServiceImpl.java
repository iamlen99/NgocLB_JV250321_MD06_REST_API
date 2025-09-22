package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.AssessmentRound;
import ra.edu.model.entity.InternshipPhase;
import ra.edu.model.request.AssessmentRoundRequest;
import ra.edu.repository.AssessmentRoundRepository;
import ra.edu.service.AssessmentRoundService;
import ra.edu.service.InternshipPhaseService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AssessmentRoundServiceImpl implements AssessmentRoundService {
    private final AssessmentRoundRepository assessmentRoundRepository;
    private final InternshipPhaseService internshipPhaseService;

    @Override
    public Page<AssessmentRound> getAllAssessmentRound(int page, int size, Long phaseId) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        if (phaseId == null) {
            return assessmentRoundRepository.findAll(pageable);
        }
        return assessmentRoundRepository.findAllByInternshipPhaseId(phaseId, pageable);
    }

    @Override
    public AssessmentRound findById(Long id) {
        return assessmentRoundRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy đợt đánh giá có id = " + id));
    }

    @Override
    public AssessmentRound createAssessmentRound(AssessmentRoundRequest assessmentRoundRequest) {
        InternshipPhase internshipPhase = internshipPhaseService.findById(assessmentRoundRequest.getPhaseId());
        AssessmentRound assessmentRound = AssessmentRound.builder()
                .internshipPhase(internshipPhase)
                .roundName(assessmentRoundRequest.getRoundName())
                .startDate(assessmentRoundRequest.getStartDate())
                .endDate(assessmentRoundRequest.getEndDate())
                .description(assessmentRoundRequest.getDescription())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        return assessmentRoundRepository.save(assessmentRound);
    }

    @Override
    public AssessmentRound updateAssessmentRound(Long id, AssessmentRoundRequest assessmentRoundRequest) {
        AssessmentRound existingAssessmentRound = findById(id);
        InternshipPhase internshipPhase = internshipPhaseService.findById(assessmentRoundRequest.getPhaseId());

        existingAssessmentRound.setInternshipPhase(internshipPhase);
        existingAssessmentRound.setRoundName(assessmentRoundRequest.getRoundName());
        existingAssessmentRound.setStartDate(assessmentRoundRequest.getStartDate());
        existingAssessmentRound.setEndDate(assessmentRoundRequest.getEndDate());
        existingAssessmentRound.setDescription(assessmentRoundRequest.getDescription());
        existingAssessmentRound.setUpdatedAt(LocalDateTime.now());
        return assessmentRoundRepository.save(existingAssessmentRound);
    }

    @Override
    public void deleteAssessmentRound(Long id) {
        AssessmentRound assessmentRound = findById(id);
        assessmentRoundRepository.delete(assessmentRound);
    }
}

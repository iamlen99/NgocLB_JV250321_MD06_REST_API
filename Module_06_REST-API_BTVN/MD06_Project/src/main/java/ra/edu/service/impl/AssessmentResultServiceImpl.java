package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ra.edu.controller_advice.custom_exception.ResourceConflictException;
import ra.edu.model.entity.*;
import ra.edu.model.request.AssessmentResultRequest;
import ra.edu.repository.AssessmentResultRepository;
import ra.edu.service.*;
import ra.edu.validation.UserRoleValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AssessmentResultServiceImpl implements AssessmentResultService {
    private final AuthService authService;
    private final AssessmentResultRepository assessmentResultRepository;
    private final InternshipAssignmentService internshipAssignmentService;
    private final AssessmentRoundService assessmentRoundService;
    private final EvaluationCriteriaService evaluationCriteriaService;
    private final UserRoleValidator userRoleValidator;

    @Override
    public Page<AssessmentResult> getAllAssessmentResult(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);

        Users currentUser = authService.getCurrentUser();
        if (userRoleValidator.isAdmin(currentUser)) {
            return assessmentResultRepository.findAll(pageable);
        } else if (userRoleValidator.isMentor(currentUser)) {
            return assessmentResultRepository.findAllByUsersUserId(currentUser.getUserId(), pageable);
        }
        return assessmentResultRepository.findAllByStudentId(currentUser.getUserId(), pageable);
    }

    private AssessmentResult findById(Long id) {
        return assessmentResultRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy kết quả đánh giá chi tiết có id = " + id));
    }

    @Override
    public AssessmentResult createAssessmentResult(AssessmentResultRequest assessmentResultRequest) {
        Users currentUser = authService.getCurrentUser();

        if (!userRoleValidator.isMentor(currentUser)) {
            throw new AccessDeniedException("Bạn không có quyền tạo kết quả đánh giá chi tiết");
        }

        InternshipAssignment internshipAssignment = internshipAssignmentService.findById(assessmentResultRequest.getAssignmentId());
        AssessmentRound assessmentRound = assessmentRoundService.findById(assessmentResultRequest.getRoundId());
        EvaluationCriteria criteria = evaluationCriteriaService.findById(assessmentResultRequest.getCriteriaId());

        if (assessmentResultRepository.existsByAssignmentAndRoundAndCriteria(
                internshipAssignment, assessmentRound, criteria)) {
            throw new ResourceConflictException("Kết quả đánh giá đã tồn tại cho vòng và tiêu chí này");
        }

        AssessmentResult assessmentResult = AssessmentResult.builder()
                .assignment(internshipAssignment)
                .round(assessmentRound)
                .criteria(criteria)
                .score(assessmentResultRequest.getScore())
                .comments(assessmentResultRequest.getComments())
                .users(currentUser)
                .evaluationDate(assessmentResultRequest.getEvaluationDate())
                .createdAt(LocalDateTime.now().withNano(0))
                .updatedAt(LocalDateTime.now().withNano(0))
                .build();
        return assessmentResultRepository.save(assessmentResult);
    }

    @Override
    public AssessmentResult updateAssessmentResult(Long id, AssessmentResultRequest assessmentResultRequest) {
        Users currentUser = authService.getCurrentUser();

        if (!userRoleValidator.isMentor(currentUser)) {
            throw new AccessDeniedException("Bạn không có quyền sửa kết quả đánh giá chi tiết");
        }

        AssessmentResult existingAssessmentResult = findById(id);
        InternshipAssignment internshipAssignment = internshipAssignmentService.findById(assessmentResultRequest.getAssignmentId());
        AssessmentRound assessmentRound = assessmentRoundService.findById(assessmentResultRequest.getRoundId());
        EvaluationCriteria criteria = evaluationCriteriaService.findById(assessmentResultRequest.getCriteriaId());

        if(assessmentResultRepository.existsByAssignmentAndRoundAndCriteriaAndResultIdNot(
                internshipAssignment, assessmentRound, criteria, id
        )) {
            throw new ResourceConflictException("Kết quả đánh giá đã tồn tại cho vòng và tiêu chí này");
        }

        existingAssessmentResult.setAssignment(internshipAssignment);
        existingAssessmentResult.setRound(assessmentRound);
        existingAssessmentResult.setCriteria(criteria);
        existingAssessmentResult.setScore(assessmentResultRequest.getScore());
        existingAssessmentResult.setComments(assessmentResultRequest.getComments());
        existingAssessmentResult.setEvaluationDate(assessmentResultRequest.getEvaluationDate());
        existingAssessmentResult.setUpdatedAt(LocalDateTime.now().withNano(0));
        return assessmentResultRepository.save(existingAssessmentResult);
    }
}

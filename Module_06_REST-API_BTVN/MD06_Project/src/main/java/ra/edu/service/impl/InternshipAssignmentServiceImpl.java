package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.edu.controller_advice.custom_exception.BadRequestException;
import ra.edu.model.entity.*;
import ra.edu.model.request.InternshipAssignmentRequest;
import ra.edu.repository.InternshipAssignmentRepository;
import ra.edu.service.*;
import ra.edu.validation.UserRoleValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InternshipAssignmentServiceImpl implements InternshipAssignmentService {
    private final AuthService authService;
    private final InternshipAssignmentRepository internshipAssignmentRepository;
    private final InternshipPhaseService internshipPhaseService;
    private final StudentService studentService;
    private final MentorService mentorService;
    private final UserRoleValidator userRoleValidator;

    @Override
    public Page<InternshipAssignment> getAllInternshipAssignment(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);

        Users currentUser = authService.getCurrentUser();
        if (userRoleValidator.isAdmin(currentUser)) {
            return internshipAssignmentRepository.findAll(pageable);
        } else if (userRoleValidator.isMentor(currentUser)) {
            return internshipAssignmentRepository.findAllByMentorMentorId(currentUser.getUserId(), pageable);
        }
        return internshipAssignmentRepository.findAllByStudentStudentId(currentUser.getUserId(), pageable);
    }

    @Override
    public InternshipAssignment findById(Long id) {
        Users currentUser = authService.getCurrentUser();
        if (userRoleValidator.isAdmin(currentUser)) {
            return internshipAssignmentRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy danh sách phân công thực tập có id = " + id));
        } else if (userRoleValidator.isMentor(currentUser)) {
            return internshipAssignmentRepository.findByIdAndMentorMentorId(id, currentUser.getUserId())
                    .orElseThrow(() -> new NoSuchElementException(
                            "Không tìm thấy phân công thực tập có id = " + id + " cho mentorId = " + currentUser.getUserId()));

        }
        return internshipAssignmentRepository.findByIdAndStudentStudentId(id, currentUser.getUserId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Không tìm thấy phân công thực tập có id = " + id + " cho studentId = " + currentUser.getUserId()));
    }

    @Override
    public InternshipAssignment createInternshipAssignment(InternshipAssignmentRequest internshipAssignmentRequest) {
        InternshipPhase internshipPhase = internshipPhaseService.findById(internshipAssignmentRequest.getPhaseId());
        Student student = studentService.findById(internshipAssignmentRequest.getStudentId());
        Mentor mentor = mentorService.findById(internshipAssignmentRequest.getMentorId());

        InternshipAssignment internshipAssignment = InternshipAssignment.builder()
                .internshipPhase(internshipPhase)
                .student(student)
                .mentor(mentor)
                .assignedDate(internshipAssignmentRequest.getAssignedDate())
                .status(AssignmentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return internshipAssignmentRepository.save(internshipAssignment);
    }

    @Override
    public InternshipAssignment updateStatusInternshipAssignment(Long id, String status) {
        InternshipAssignment existingInternshipAssignment = findById(id);

        try {
            AssignmentStatus newStatus = AssignmentStatus.valueOf(status.toUpperCase());
            existingInternshipAssignment.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Trạng thái phân công thực tập không hợp lệ: " + status);
        }

        existingInternshipAssignment.setUpdatedAt(LocalDateTime.now());
        return internshipAssignmentRepository.save(existingInternshipAssignment);
    }

}

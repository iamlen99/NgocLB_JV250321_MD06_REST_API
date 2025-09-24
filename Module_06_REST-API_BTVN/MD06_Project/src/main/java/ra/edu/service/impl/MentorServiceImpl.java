package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Mentor;
import ra.edu.model.entity.Users;
import ra.edu.model.request.MentorRequest;
import ra.edu.model.response.MentorDetailsResponse;
import ra.edu.model.response.MentorResponse;
import ra.edu.repository.MentorRepository;
import ra.edu.service.AuthService;
import ra.edu.service.MentorService;
import ra.edu.validation.MentorValidator;
import ra.edu.validation.UserRoleValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {
    private final MentorRepository mentorRepository;
    private final AuthService authService;
    private final UserRoleValidator  userRoleValidator;
    private final MentorValidator mentorValidator;


    @Override
    public Page<MentorResponse> getAllMentors(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        return mentorRepository.findAllMentors(pageable);
    }

    @Override
    public Page<MentorDetailsResponse> getAllMentorsDetails(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        return mentorRepository.findAllMentorsDetails(pageable);
    }

    @Override
    public MentorDetailsResponse findDetailsById(Long id) {
        Users currentUser = authService.getCurrentUser();
        if (userRoleValidator.isMentor(currentUser)) {
            if (currentUser.getUserId().equals(id)) {
                return mentorRepository.findMentorsDetailsByMentorId(currentUser.getUserId())
                        .orElseThrow(() -> new NoSuchElementException("Có lỗi trong quá trình lấy thông tin, xin thử lại"));
            }
            throw new AccessDeniedException("Bạn chỉ được lấy thông tin theo id của mình là " + currentUser.getUserId());
        }
        return mentorRepository.findMentorsDetailsByMentorId(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy mentor có id = " + id));
    }

    @Override
    public Mentor findById(Long id) {
        return mentorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Có lỗi trong quá trình lấy thông tin, xin thử lại"));
    }

    @Override
    public Mentor createMentor(MentorRequest mentorRequest) {
        Users user = mentorValidator.validateMentorInfo(mentorRequest);

        Mentor mentor = Mentor.builder()
                .user(user)
                .department(mentorRequest.getDepartment())
                .academicRank(mentorRequest.getAcademicRank())
                .createdAt(LocalDateTime.now().withNano(0))
                .updatedAt(LocalDateTime.now().withNano(0))
                .build();
        return mentorRepository.save(mentor);
    }

    @Override
    public Mentor updateMentor(Long mentorId, MentorRequest mentorRequest) {
        Users currentUser = authService.getCurrentUser();

        Mentor existingMentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Không tìm thấy mentor có id = " + mentorId));

        if (userRoleValidator.isMentor(currentUser) && !mentorId.equals(currentUser.getUserId())) {
            throw new AccessDeniedException("Bạn chỉ được cập nhật thông tin theo id của mình là " + currentUser.getUserId());
        }

        existingMentor.setDepartment(mentorRequest.getDepartment());
        existingMentor.setAcademicRank(mentorRequest.getAcademicRank());
        existingMentor.setUpdatedAt(LocalDateTime.now().withNano(0));

        return mentorRepository.save(existingMentor);
    }
}

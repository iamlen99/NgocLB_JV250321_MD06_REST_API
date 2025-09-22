package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.RoleName;
import ra.edu.model.entity.Mentor;
import ra.edu.model.entity.Users;
import ra.edu.model.request.MentorRequest;
import ra.edu.repository.MentorRepository;
import ra.edu.repository.UserRepository;
import ra.edu.service.MentorService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {
    private final MentorRepository mentorRepository;
    private final UserRepository userRepository;

    @Override
    public Page<Mentor> getAllMentor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return mentorRepository.findAll(pageable);
    }

    @Override
    public Mentor findById(Long id) {
        return mentorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy mentor có id = " + id));
    }

    @Override
    public Mentor createMentor(MentorRequest mentorRequest) {

        Users user = userRepository.findById(mentorRequest.getMentorId())
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy người dùng có id = " + mentorRequest.getMentorId()));
        boolean hasMentorRole = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName() == RoleName.MENTOR);

        if (!hasMentorRole) {
            throw new IllegalArgumentException("Chỉ được tạo thông tin mentor mới khi người dùng là MENTOR");
        }

        Mentor mentor = Mentor.builder()
                .user(user)
                .department(mentorRequest.getDepartment())
                .academicRank(mentorRequest.getAcademicRank())
                .createdAt(LocalDateTime.now())
                .build();
        return mentorRepository.save(mentor);
    }

    @Override
    public Mentor updateMentor(MentorRequest mentorRequest) {
        Mentor existingMentor = mentorRepository.findById(mentorRequest.getMentorId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Không tìm thấy mentor có id = " + mentorRequest.getMentorId()));

        Users user = existingMentor.getUser();
        boolean hasMentorRole = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName() == RoleName.MENTOR);

        if (!hasMentorRole) {
            throw new IllegalArgumentException("Chỉ được cập nhật thông tin mentor khi người dùng là MENTOR");
        }

        existingMentor.setDepartment(mentorRequest.getDepartment());
        existingMentor.setAcademicRank(mentorRequest.getAcademicRank());
        existingMentor.setUpdatedAt(LocalDateTime.now());

        return mentorRepository.save(existingMentor);
    }
}

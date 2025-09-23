package ra.edu.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ra.edu.controller_advice.custom_exception.BadRequestException;
import ra.edu.controller_advice.custom_exception.ResourceConflictException;
import ra.edu.model.entity.Mentor;
import ra.edu.model.entity.Users;
import ra.edu.model.request.MentorRequest;
import ra.edu.repository.MentorRepository;
import ra.edu.service.UserService;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class MentorValidator {
    private final MentorRepository mentorRepository;
    private final UserRoleValidator userRoleValidator;
    private final UserService userService;

    public Users validateMentorInfo(MentorRequest mentorRequest) {
        Users user = userService.findById(mentorRequest.getMentorId());

        if (!userRoleValidator.isMentor(user)) {
            throw new BadRequestException("User này không có quyền MENTOR");
        }

        if (mentorRepository.existsById(user.getUserId())) {
            throw new ResourceConflictException("User này đã có thông tin Mentor rồi");
        }
        return user;
    }
}

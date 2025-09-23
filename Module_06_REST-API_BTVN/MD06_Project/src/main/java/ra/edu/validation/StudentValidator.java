package ra.edu.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ra.edu.controller_advice.custom_exception.BadRequestException;
import ra.edu.controller_advice.custom_exception.ResourceConflictException;
import ra.edu.model.entity.Student;
import ra.edu.model.entity.Users;
import ra.edu.model.request.StudentRequest;
import ra.edu.repository.StudentRepository;
import ra.edu.service.UserService;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class StudentValidator {
    private final StudentRepository studentRepository;
    private final UserRoleValidator userRoleValidator;
    private final UserService userService;

    public Users validateStudentInfo(StudentRequest studentRequest) {
        Users user = userService.findById(studentRequest.getUserId());

        if (!userRoleValidator.isStudent(user)) {
            throw new BadRequestException("User này không có quyền STUDENT");
        }

        if (studentRepository.existsById(user.getUserId())) {
            throw new ResourceConflictException("User này đã có thông tin Student");
        }

        if (studentRepository.existsByStudentCode(studentRequest.getStudentCode())) {
            throw new ResourceConflictException("Mã sinh viên đã tồn tại!");
        }
        return user;
    }

    public Student validateStudentInfo(Long studentId, StudentRequest studentRequest) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Không tìm thấy sinh viên có id = " + studentId));

        Student studentByCode = studentRepository.findByStudentCode(studentRequest.getStudentCode());

        if (studentByCode != null && !studentByCode.getStudentId().equals(studentId)) {
            throw new ResourceConflictException("Mã sinh viên đã tồn tại!");
        }

        return existingStudent;
    }
}

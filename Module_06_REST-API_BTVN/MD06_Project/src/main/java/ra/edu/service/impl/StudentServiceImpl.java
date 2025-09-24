package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Student;
import ra.edu.model.entity.Users;
import ra.edu.model.request.StudentRequest;
import ra.edu.model.response.StudentResponse;
import ra.edu.model.response.StudentsDetailsResponse;
import ra.edu.repository.StudentRepository;
import ra.edu.service.AuthService;
import ra.edu.service.StudentService;
import ra.edu.validation.StudentValidator;
import ra.edu.validation.UserRoleValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final AuthService authService;
    private final UserRoleValidator userRoleValidator;
    private final StudentValidator studentValidator;

    @Override
    public Page<StudentResponse> getAllStudent(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        Users currentUser = authService.getCurrentUser();
        if (userRoleValidator.isAdmin(currentUser)) {
            return studentRepository.findAllMentor(pageable);
        }
        if (userRoleValidator.isMentor(currentUser)) {
            return studentRepository.findAllByMentorId(currentUser.getUserId(), pageable);
        }
        throw new AccessDeniedException("Bạn không có quyền truy cập");
    }

    @Override
    public StudentsDetailsResponse findDetailsById(Long id) {
        Users currentUser = authService.getCurrentUser();
        if (userRoleValidator.isAdmin(currentUser)) {
            return studentRepository.findByStudentId(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy sinh viên có id = " + id));
        } else if (userRoleValidator.isMentor(currentUser)) {
            return studentRepository.findByStudentIdAndMentorId(id, currentUser.getUserId())
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy sinh viên có id = " + id
                            + " hoặc sinh viên này không thuộc danh sách phân công của bạn"));
        }
        if (!currentUser.getUserId().equals(id)) {
            throw new AccessDeniedException("Bạn chỉ được xem thông tin của mình theo id là " + currentUser.getUserId());
        }
        return studentRepository.findByStudentId(currentUser.getUserId())
                .orElseThrow(() -> new NoSuchElementException("Có lỗi khi lấy thông tin sinh viên, xin thử lại"));
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Không tìm thấy sinh viên có id = " + id));
    }

    @Override
    public Student createStudent(StudentRequest studentRequest) {
        Users user = studentValidator.validateStudentInfo(studentRequest);

        Student student = Student.builder()
                .user(user)
                .studentCode(studentRequest.getStudentCode())
                .major(studentRequest.getMajor())
                .studentClass(studentRequest.getStudentClass())
                .dateOfBirth(studentRequest.getDateOfBirth())
                .address(studentRequest.getAddress())
                .createdAt(LocalDateTime.now().withNano(0))
                .updatedAt(LocalDateTime.now().withNano(0))
                .build();
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long studentId, StudentRequest studentRequest) {
        Users currentUser = authService.getCurrentUser();

        if (userRoleValidator.isStudent(currentUser) && !currentUser.getUserId().equals(studentId)) {
            throw new AccessDeniedException("Bạn chỉ được cập nhật thông tin của mình với id là " + currentUser.getUserId());
        }
        Student existingStudent = studentValidator.validateStudentInfo(studentId, studentRequest);

        existingStudent.setStudentCode(studentRequest.getStudentCode());
        existingStudent.setMajor(studentRequest.getMajor());
        existingStudent.setStudentClass(studentRequest.getStudentClass());
        existingStudent.setDateOfBirth(studentRequest.getDateOfBirth());
        existingStudent.setAddress(studentRequest.getAddress());
        existingStudent.setUpdatedAt(LocalDateTime.now().withNano(0));

        return studentRepository.save(existingStudent);
    }
}

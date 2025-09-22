package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.edu.controller_advice.custom_exception.ResourceConflictException;
import ra.edu.model.entity.RoleName;
import ra.edu.model.entity.Student;
import ra.edu.model.entity.Users;
import ra.edu.model.request.StudentRequest;
import ra.edu.repository.StudentRepository;
import ra.edu.repository.UserRepository;
import ra.edu.service.StudentService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    public Page<Student> getAllStudent(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy sinh viên có id = " + id));
    }

    @Override
    public Student createStudent(StudentRequest studentRequest) {
        if (studentRepository.existsByStudentCode(studentRequest.getStudentCode())) {
            throw new ResourceConflictException("Mã sinh viên đã tồn tại!");
        }

        Users user = userRepository.findById(studentRequest.getStudentId())
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy người dùng có id = " + studentRequest.getStudentId()));
        boolean hasStudentRole = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName() == RoleName.STUDENT);

        if (!hasStudentRole) {
            throw new IllegalArgumentException("Chỉ được tạo thông tin sinh viên mới khi người dùng là STUDENT");
        }

        Student student = Student.builder()
                .user(user)
                .studentCode(studentRequest.getStudentCode())
                .major(studentRequest.getMajor())
                .studentClass(studentRequest.getStudentClass())
                .dateOfBirth(studentRequest.getDateOfBirth())
                .address(studentRequest.getAddress())
                .createdAt(LocalDateTime.now())
                .build();
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(StudentRequest studentRequest) {
        Student existingStudent = studentRepository.findById(studentRequest.getStudentId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Không tìm thấy sinh viên có id = " + studentRequest.getStudentId()));

        Student studentByCode = studentRepository.findByStudentCode(studentRequest.getStudentCode());
        if (studentByCode != null && !studentByCode.getStudentId().equals(studentRequest.getStudentId())) {
            throw new ResourceConflictException("Mã sinh viên đã tồn tại!");
        }

        Users user = existingStudent.getUser(); // lấy từ quan hệ thay vì query lại
        boolean hasStudentRole = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName() == RoleName.STUDENT);

        if (!hasStudentRole) {
            throw new IllegalArgumentException("Chỉ được cập nhật thông tin sinh viên khi người dùng là STUDENT");
        }

        existingStudent.setStudentCode(studentRequest.getStudentCode());
        existingStudent.setMajor(studentRequest.getMajor());
        existingStudent.setStudentClass(studentRequest.getStudentClass());
        existingStudent.setDateOfBirth(studentRequest.getDateOfBirth());
        existingStudent.setAddress(studentRequest.getAddress());
        existingStudent.setUpdatedAt(LocalDateTime.now());

        return studentRepository.save(existingStudent);
    }
}

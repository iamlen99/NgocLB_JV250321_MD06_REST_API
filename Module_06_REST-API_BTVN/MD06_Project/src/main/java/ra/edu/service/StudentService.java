package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.Student;
import ra.edu.model.request.StudentRequest;

public interface StudentService {
    Page<Student> getAllStudent(int page, int size);

    Student findById(Long id);

    Student createStudent(StudentRequest studentRequest);

    Student updateStudent(StudentRequest studentRequest);
}

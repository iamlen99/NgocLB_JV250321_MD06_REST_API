package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.Student;
import ra.edu.model.request.StudentRequest;
import ra.edu.model.response.StudentResponse;
import ra.edu.model.response.StudentsDetailsResponse;

public interface StudentService {
    Page<StudentResponse> getAllStudent(int page, int size);

    StudentsDetailsResponse findDetailsById(Long id);

    Student findById(Long id);

    Student createStudent(StudentRequest studentRequest);

    Student updateStudent(Long studentId, StudentRequest studentRequest);
}

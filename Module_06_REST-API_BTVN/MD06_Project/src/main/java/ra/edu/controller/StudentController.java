package ra.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.model.entity.Student;
import ra.edu.model.request.StudentRequest;
import ra.edu.model.response.ApiDataResponse;
import ra.edu.service.StudentService;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<Student>> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        Student student = studentService.createStudent(studentRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiDataResponse.success(student, "Tạo thông tin sinh viên thành công"));
    }

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<Student>>> getAllStudents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<Student> students = studentService.getAllStudent(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(students, "Lấy danh sách sinh viên thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Student>> getStudentById(
            @PathVariable Long id
    ) {
        Student student = studentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(student, "Lấy thông tin sinh viên thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Student>> updateStudentById(
            @Valid @RequestBody StudentRequest studentRequest
    ) {
        Student studentUpdate = studentService.updateStudent(studentRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiDataResponse.success(studentUpdate, "Cập nhật thông tin sinh viên thành công"));
    }
}

package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.dto.StudentCountPerCourse;

import java.util.List;

public interface StatisticService {
    Long countAllCourses();
    Long countAllStudents();
    Long countEnrolledStudents();
    Page<StudentCountPerCourse> getStudentCountPerCourse(Integer page, Integer size);
    Page<StudentCountPerCourse> getTop5CoursesMostEnrolled();
}

package ra.edu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.edu.model.dto.CourseDTO;
import ra.edu.model.entity.Course;

import java.util.Optional;

public interface CourseService {
    Page<Course> getAllCourses(Integer page, Integer size, String sortBy);
    Page<Course> getAllCoursesByName(Integer page, Integer size, String courseName,  String sortBy);
    Course save(Course course);
    boolean isExistName(String courseName);
    Optional<Course> findCourseById(Long id);
    boolean deleteCourseById(Long id);
    boolean existsByName(String name);
}

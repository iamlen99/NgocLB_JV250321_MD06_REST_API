package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Course;
import ra.edu.repository.CourseRepository;
import ra.edu.service.CourseService;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    private Pageable buildPageable(Integer page, Integer size, String sortBy) {
        if (sortBy == null || sortBy.isBlank()) {
            return PageRequest.of(page, size);
        }

        Sort sortOrder = switch (sortBy) {
            case "nameASC" -> Sort.by(Sort.Direction.ASC, "name");
            case "nameDESC" -> Sort.by(Sort.Direction.DESC, "name");
            case "idASC" -> Sort.by(Sort.Direction.ASC, "id");
            case "idDESC" -> Sort.by(Sort.Direction.DESC, "id");
            default -> Sort.unsorted();
        };

        return PageRequest.of(page, size, sortOrder);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Page<Course> getAllCourses(Integer page, Integer size, String sortBy) {
        Pageable pageable = buildPageable(page, size, sortBy);
        return courseRepository.findAll(pageable);
    }

    @Override
    public Page<Course> getAllCoursesByName(Integer page, Integer size, String courseName, String sortBy) {
        Pageable pageable = buildPageable(page, size, sortBy);
        return courseRepository.findAllByNameContaining(courseName, pageable);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public boolean isExistName(String courseName) {
        return courseRepository.existsByName(courseName);
    }

    @Override
    public Optional<Course> findCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public boolean deleteCourseById(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByName(String name) {
        return courseRepository.existsByName(name);
    }
}

package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.edu.model.dto.StudentCountPerCourse;
import ra.edu.model.entity.*;
import ra.edu.repository.CourseRepository;
import ra.edu.repository.EnrollmentRepository;
import ra.edu.repository.UserRepository;
import ra.edu.service.StatisticService;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Long countAllCourses() {
        return courseRepository.count();
    }

    @Override
    public Long countAllStudents() {
        return userRepository.countByRole(Role.STUDENT);
    }

    @Override
    public Long countEnrolledStudents() {
        return enrollmentRepository.countDistinctUserIdByStatus(EnrollmentStatus.CONFIRMED);
    }

    @Override
    public Page<StudentCountPerCourse> getTop5CoursesMostEnrolled() {
        return enrollmentRepository.getTop5CoursesMostEnrolled(PageRequest.of(0, 5));
    }

    @Override
    public Page<StudentCountPerCourse> getStudentCountPerCourse(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentRepository.getStudentCountPerCourse(pageable);
    }
}

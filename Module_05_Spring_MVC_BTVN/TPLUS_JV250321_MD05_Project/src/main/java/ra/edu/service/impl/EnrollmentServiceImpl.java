package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Enrollment;
import ra.edu.model.entity.EnrollmentStatus;
import ra.edu.repository.EnrollmentRepository;
import ra.edu.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    private Enrollment getWaitingEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalStateException("Không tìm thấy đăng ký có id = " + enrollmentId));

        if (enrollment.getStatus() != EnrollmentStatus.WAITING) {
            throw new IllegalStateException("Chỉ được thao tác khi trạng thái đăng ký là WAITING");
        }
        return enrollment;
    }

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment getEnrollmentByCourseIdAndStudentId(Long courseId, Long studentId) {
        return enrollmentRepository.findByCourseIdAndUser_Id(courseId, studentId);
    }

    @Override
    public Page<Enrollment> getEnrollmentByStudentId(Long studentId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentRepository.findByUserId(studentId, pageable);
    }

    @Override
    public Page<Enrollment> getEnrollmentByStudentIdAndSearchValue(Long studentId, Integer page, Integer size, String searchValue) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentRepository.findByUserIdAndCourseNameContaining(studentId, searchValue, pageable);
    }

    @Override
    public Page<Enrollment> getAllEnrollments(Integer page, Integer size, EnrollmentStatus status) {
        Pageable pageable = PageRequest.of(page, size);
        if (status == null) {
            return enrollmentRepository.findAll(pageable);
        }
        return enrollmentRepository.findAllByStatus(status, pageable);
    }

    @Override
    public Page<Enrollment> getEnrollmentsByCourseName(Integer page, Integer size, String searchValue, EnrollmentStatus status) {
        Pageable pageable = PageRequest.of(page, size);
        if (status == null) {
            return enrollmentRepository.findAllByCourseNameContaining(searchValue, pageable);
        }
        return enrollmentRepository.findAllByCourseNameContainingAndStatus(searchValue, status, pageable);
    }

    @Override
    public void confirmEnrollment(Long enrollmentId) {
        Enrollment enrollment = getWaitingEnrollment(enrollmentId);
        enrollment.setStatus(EnrollmentStatus.CONFIRMED);
        enrollmentRepository.save(enrollment);
    }

    @Override
    public void denyEnrollment(Long enrollmentId) {
        Enrollment enrollment = getWaitingEnrollment(enrollmentId);
        enrollment.setStatus(EnrollmentStatus.DENIED);
        enrollmentRepository.save(enrollment);
    }

    @Override
    public void cancelEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalStateException("Không tìm thấy bản đăng ký có id = " + enrollmentId));
        switch (enrollment.getStatus()) {
            case CONFIRMED -> throw new IllegalStateException("Bạn đã được duyệt đăng ký rồi, không thể hủy");
            case CANCELLED -> throw new IllegalStateException("Bạn đã hủy bản đăng ký này rồi");
            case DENIED -> throw new IllegalStateException("Bạn đã bị từ chối đăng ký, không thể hủy");
            default -> {
                enrollment.setStatus(EnrollmentStatus.CANCELLED);
                enrollmentRepository.save(enrollment);
            }
        }
    }
}

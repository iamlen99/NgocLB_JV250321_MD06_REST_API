package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> findAll();
    Page<Exam> findAll(int page, int size);
    Exam save(Exam exam);
    Exam findById(Long id);
    boolean deleteById(Long id);
}

package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.User;

import java.util.Optional;

public interface StudentService {
    Page<User> getAllStudents(Integer page, Integer size, String sortBy);
    Page<User> getAllStudentsByName(Integer page, Integer size, String searchValue,  String sortBy);
    Optional<User> getStudentById(Long id);
    boolean toggleStudentStatus(Long id);
}

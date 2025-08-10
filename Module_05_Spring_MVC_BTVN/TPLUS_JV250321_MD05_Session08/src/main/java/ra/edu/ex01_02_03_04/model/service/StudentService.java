package ra.edu.ex01_02_03_04.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ex01_02_03_04.model.entity.Student;
import ra.edu.ex01_02_03_04.model.repository.StudentRepository;


import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public boolean addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findStudentById(int id) {
        return studentRepository.findById(id);
    }

    public boolean updateStudent(Student student) {
        return studentRepository.update(student);
    }

    public boolean deleteStudent(int id) {
        return studentRepository.delete(id);
    }
}

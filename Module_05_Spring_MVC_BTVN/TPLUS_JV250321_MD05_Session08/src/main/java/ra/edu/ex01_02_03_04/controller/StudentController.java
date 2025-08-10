package ra.edu.ex01_02_03_04.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.edu.ex01_02_03_04.model.entity.Student;
import ra.edu.ex01_02_03_04.model.service.StudentService;

import java.util.Optional;

@Controller
@RequestMapping("/studentController")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String displayAllStudents(Model model) {
        model.addAttribute("studentList", studentService.findAll());
        return "student/studentList";
    }

    @GetMapping("/goToAddPage")
    public String goToAddPage(Model model) {
        model.addAttribute("student", new Student());
        return "student/addStudent";
    }

    @GetMapping("/goToUpdatePage")
    public String goToUpdatePage(@RequestParam("id") int id, Model model) {
        Optional<Student> student = studentService.findStudentById(id);
        if (student.isEmpty()) {
            model.addAttribute("error", "Có lỗi trong quá trình lấy id sinh viên");
            return "student/studentList";
        }
        model.addAttribute("student", student.get());
        return "student/updateStudent";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model) {
        if (studentService.deleteStudent(id)) {
            model.addAttribute("studentList", studentService.findAll());
            model.addAttribute("message", "Xóa sinh viên thành công");
        } else {
            model.addAttribute("error", "Có lỗi trong quá trình xóa sinh viên, vui lòng thử lại!");
        }
        return "student/studentList";
    }

    @PostMapping("/addStudent")
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "student/addStudent";
        }
        if (studentService.addStudent(student)) {
            model.addAttribute("studentList", studentService.findAll());
            model.addAttribute("message", "Thêm sinh viên thành công");
            return "student/studentList";
        }
        model.addAttribute("student", student);
        model.addAttribute("error", "Có lỗi trong quá trình thêm sinh viên, vui lòng thử lại!");
        return "student/addStudent";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "student/updateStudent";
        }
        if (studentService.updateStudent(student)) {
            model.addAttribute("studentList", studentService.findAll());
            model.addAttribute("message", "Thay đổi thông tin sinh viên thành công");
            return "student/studentList";
        }
        model.addAttribute("student", student);
        model.addAttribute("error", "Có lỗi trong quá trình thay đổi thông tin sinh viên, vui lòng thử lại!");
        return "student/updateStudent";
    }
}

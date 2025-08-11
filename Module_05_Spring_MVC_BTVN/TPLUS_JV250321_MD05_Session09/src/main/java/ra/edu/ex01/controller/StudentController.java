package ra.edu.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.edu.ex01.entity.Student;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/studentController")
public class StudentController {
    private final List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Nguyen Van A", 20, "CNTT1", "a@example.com", "Ha Noi", "0901234567"),
            new Student(2, "Tran Thi B", 21, "CNTT1", "b@example.com", "Hai Phong", "0912345678"),
            new Student(3, "Le Van C", 22, "CNTT2", "c@example.com", "Da Nang", "0923456789"),
            new Student(4, "Pham Thi D", 19, "CNTT2", "d@example.com", "Hue", "0934567890"),
            new Student(5, "Hoang Van E", 20, "CNTT3", "e@example.com", "Nam Dinh", "0945678901"),
            new Student(6, "Do Thi F", 21, "CNTT3", "f@example.com", "Ninh Binh", "0956789012"),
            new Student(7, "Bui Van G", 23, "CNTT4", "g@example.com", "Thanh Hoa", "0967890123"),
            new Student(8, "Dang Thi H", 22, "CNTT4", "h@example.com", "Quang Ninh", "0978901234"),
            new Student(9, "Vo Van I", 20, "CNTT5", "i@example.com", "Nghe An", "0989012345"),
            new Student(10, "Ngo Thi J", 19, "CNTT5", "j@example.com", "Ha Tinh", "0990123456")
    ));

    @GetMapping
    public String displayStudentList(Model model) {
        model.addAttribute("students", students);
        return "ex01/student-list";
    }
}

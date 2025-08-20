package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.dto.UserNameDT0;
import ra.edu.model.entity.Exam;
import ra.edu.service.ExamService;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ExamService examService;

    @GetMapping
    public String showList(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
                            HttpSession session,
            Model model) {
        UserNameDT0 currentUser = (UserNameDT0) session.getAttribute("userNameDTO");
        Page<Exam> exams = examService.findAll(page, size);
        model.addAttribute("exams", exams);
        model.addAttribute("currentUser", currentUser);
        return "home";
    }

}

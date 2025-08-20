package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.entity.Exam;
import ra.edu.service.ExamService;

@Controller
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping
    public String showList(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<Exam> exams = examService.findAll(page, size);
        model.addAttribute("exams", exams);
        return "exam/listExams";
    }

    @GetMapping("/add")
    public String showAdd(Model model) {
        Exam exam = new Exam();
        model.addAttribute("exam", exam);
        return "exam/addExam";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        Exam exam = examService.findById(id);
        if (exam != null) {
            model.addAttribute("exam", exam);
            return "exam/editExam";
        }
        redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình lấy id, xin thử lại");
        return "redirect:/exams";
    }

    @GetMapping("/delete/{id}")
    public String deleteExam(@PathVariable("id") Long id,
                             RedirectAttributes redirectAttributes) {
        if (examService.deleteById(id)) {
            redirectAttributes.addFlashAttribute("successMsg", "Xóa bài thi thành công");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình lấy id, xin thử lại");
        }
        return "redirect:/exams";
    }

    @PostMapping("/add")
    public String addExam(@Valid @ModelAttribute("exam") Exam exam,
                          BindingResult result,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        if (result.hasErrors()) {
            return "exam/addExam";
        }
        Exam addedExam = examService.save(exam);
        if (addedExam != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm bài thi thành công");
            return "redirect:/exams";
        }
        model.addAttribute("errMsg", "Thêm bài thi thất bại, vui lòng thử lại");
        return "exam/addExam";
    }

    @PostMapping("/update")
    public String updateExam(@Valid @ModelAttribute("exam") Exam exam,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (result.hasErrors()) {
            return "exam/editExam";
        }
        Exam updatedExam = examService.save(exam);
        if (updatedExam != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Sửa bài thi thành công");
            return "redirect:/exams";
        }
        model.addAttribute("errMsg", "Sửa bài thi thất bại, vui lòng thử lại");
        return "exam/editExam";
    }
}

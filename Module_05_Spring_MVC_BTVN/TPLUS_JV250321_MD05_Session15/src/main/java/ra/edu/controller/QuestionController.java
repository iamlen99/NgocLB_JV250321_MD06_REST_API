package ra.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.entity.Exam;
import ra.edu.model.entity.Question;
import ra.edu.service.ExamService;
import ra.edu.service.QuestionService;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExamService examService;

    @GetMapping
    public String showList(Model model) {
        List<Question> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        return "question/listQuestions";
    }

    @GetMapping("/add")
    public String showAdd(Model model,
                          RedirectAttributes redirectAttributes) {
        Question question = new Question();
        model.addAttribute("question", question);
        List<Exam> exams = examService.findAll();
        if (exams.isEmpty()) {
            redirectAttributes.addFlashAttribute("errMsg", "Chưa có đề thi nào. Hãy thêm đề thi trước khi tạo câu hỏi.");
            return "redirect:/home";
        }
        model.addAttribute("exams", exams);
        return "question/addQuestion";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        Question question = questionService.findById(id);
        if (question != null) {
            model.addAttribute("question", question);
            model.addAttribute("exams", examService.findAll());
            return "question/editQuestion";
        }
        redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình lấy id, xin thử lại");
        return "redirect:/questions";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Long id,
                             RedirectAttributes redirectAttributes) {
        if (questionService.deleteById(id)) {
            redirectAttributes.addFlashAttribute("successMsg", "Xóa câu hỏi thành công");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi trong quá trình lấy id, xin thử lại");
        }
        return "redirect:/questions";
    }

    @PostMapping("/add")
    public String addQuestion(@Valid @ModelAttribute("question") Question question,
                          BindingResult result,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("exams", examService.findAll());
            return "question/addQuestion";
        }
        Exam exam = examService.findById(question.getExam().getId());
        question.setExam(exam);
        Question addedQuestion = questionService.save(question);
        if (addedQuestion != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Thêm câu hỏi thành công");
            return "redirect:/questions";
        }
        model.addAttribute("exams", examService.findAll());
        model.addAttribute("errMsg", "Thêm câu hỏi thất bại, vui lòng thử lại");
        return "question/addQuestion";
    }

    @PostMapping("/update")
    public String updateQuestion(@Valid @ModelAttribute("question") Question question,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("exams", examService.findAll());
            return "question/editQuestion";
        }

        Exam exam = examService.findById(question.getExam().getId());
        question.setExam(exam);
        Question updatedQuestion = questionService.save(question);
        if (updatedQuestion != null) {
            redirectAttributes.addFlashAttribute("successMsg", "Sửa câu hỏi thành công");
            return "redirect:/questions";
        }
        model.addAttribute("exams", examService.findAll());
        model.addAttribute("errMsg", "Sửa câu hỏi thất bại, vui lòng thử lại");
        return "question/editQuestion";
    }
}

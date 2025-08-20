package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.edu.model.entity.Question;
import ra.edu.service.QuestionService;

import java.util.List;

@Controller
@RequestMapping("/play")
public class PlayController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/exam/{examId}")
    public String showExam(@PathVariable("examId") Long examId,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        List<Question> questions = questionService.findByExamId(examId);
        if (questions.isEmpty()) {
            redirectAttributes.addFlashAttribute("errMsg", "Đề thi này chưa có câu hỏi");
            return "redirect:/home";
        }
        Question question = questions.get(0);
        model.addAttribute("question", question);
        return "question/question";
    }

    @PostMapping("/play/exam/{examId}/question/{id}")
    public String showResult(@PathVariable("examId") Long examId,
                             @PathVariable("id") Long id,
                             @RequestParam("answer") String answer,
                             RedirectAttributes redirectAttributes) {
        Question question = questionService.findByExamIdAndId(examId, id);
        if (question == null) {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi khi lấy đáp án câu hỏi");
            return "redirect:/play/exam/" + examId;
        }
        if (question.getAnswerTrue().equals(answer)) {
            redirectAttributes.addFlashAttribute("successMsg", "Bạn đã trả lời đúng!");
        } else {
            redirectAttributes.addFlashAttribute("errMsg", "Bạn đã trả lời sai!");
        }
        return "redirect:/play/exam/" + examId;
    }
}

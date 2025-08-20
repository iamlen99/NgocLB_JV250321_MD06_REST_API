package ra.edu.controller;

import jakarta.servlet.http.HttpSession;
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
                           RedirectAttributes redirectAttributes,
                           HttpSession session) {
        List<Question> questions = questionService.findByExamId(examId);
        if (questions.isEmpty()) {
            redirectAttributes.addFlashAttribute("errMsg", "Đề thi này chưa có câu hỏi");
            return "redirect:/home";
        }
        session.setAttribute("examQuestions", questions);
        session.setAttribute("currentQuestionIndex", 0);

        Question firstQuestion = questions.get(0);
        model.addAttribute("question", firstQuestion);
        model.addAttribute("examId", examId);
        return "question/question";
    }

    @PostMapping("/exam/{examId}/answer")
    public String showResult(@PathVariable("examId") Long examId,
                             @RequestParam("questionId") Long questionId,
                             @RequestParam("answer") String answer,
                             RedirectAttributes redirectAttributes,
                             Model model,
                             HttpSession session) {
        List<Question> questions = (List<Question>) session.getAttribute("examQuestions");
        Integer currentIndex = (Integer) session.getAttribute("currentQuestionIndex");
        if (questions == null || currentIndex == null || currentIndex >= questions.size()) {
            redirectAttributes.addFlashAttribute("errMsg", "Đã có lỗi xảy ra hoặc bạn đã hoàn thành bài thi.");
            return "redirect:/home";
        }

        Question currentQuestion = questions.get(currentIndex);
        if (!currentQuestion.getId().equals(questionId)) {
            redirectAttributes.addFlashAttribute("errMsg", "Có lỗi khi xử lý câu hỏi.");
            return "redirect:/play/exam/" + examId;
        }

        if (currentQuestion.getAnswerTrue().equals(answer)) {
            model.addAttribute("successMsg", "Bạn đã trả lời đúng!");
        } else {
            model.addAttribute("errMsg", "Bạn đã trả lời sai!");
        }
        model.addAttribute("questionContent", currentQuestion.getContent());
        model.addAttribute("trueAnswer", currentQuestion.getAnswerTrue());
        model.addAttribute("yourAnswer", answer);
        session.setAttribute("currentQuestionIndex", currentIndex + 1);
        return "result";
    }

    @GetMapping("/exam/{examId}/next")
    public String showNextQuestion(@PathVariable("examId") Long examId,
                                   Model model,
                                   RedirectAttributes redirectAttributes,
                                   HttpSession session) {
        List<Question> questions = (List<Question>) session.getAttribute("examQuestions");
        Integer currentIndex = (Integer) session.getAttribute("currentQuestionIndex");
        if (questions == null || currentIndex == null || currentIndex >= questions.size()) {
            redirectAttributes.addFlashAttribute("successMsg", "Bạn đã hoàn thành bài thi!");
            session.removeAttribute("examQuestions");
            session.removeAttribute("currentQuestionIndex");
            return "redirect:/home";
        }
        Question nextQuestion = questions.get(currentIndex);
        model.addAttribute("question", nextQuestion);
        model.addAttribute("examId", examId);
        return "question/question";
    }
}

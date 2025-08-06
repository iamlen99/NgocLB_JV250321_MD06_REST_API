package ra.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.exercise.model.entity.Question;
import ra.exercise.model.service.QuizService;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/quizController"})
public class QuizController {
    private static Question currentQuestion;
    private static int attemps = 0;

    @Autowired
    private QuizService quizService;

    @GetMapping("/")
    public String getRandomQuestion(Model model) {
        Question question = quizService.getRandomQuestion();
        currentQuestion = question;
        model.addAttribute("question", question);
        return "quiz";
    }

    @PostMapping("/guess")
    public String guessAnswer(@RequestParam("answer") String answer, Model model) {
        if (quizService.checkAnswer(currentQuestion, answer)) {
            model.addAttribute("correct", "Da doan dung");
            attemps = 0;
        } else {
            attemps++;
            if(attemps < 3) {
                model.addAttribute("wrong", "Da doan sai, ban con " + (3 - attemps) + " luot");
            } else {
                model.addAttribute("wrong", "Ban het luot doan");
                model.addAttribute("playagain", "Choi lai");
                model.addAttribute("noGuess", "Xoa button");
                attemps = 0;
            }
        }
        model.addAttribute("question", currentQuestion);
        return "quiz";
    }
}

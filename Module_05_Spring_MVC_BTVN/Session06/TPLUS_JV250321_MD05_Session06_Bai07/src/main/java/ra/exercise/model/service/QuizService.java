package ra.exercise.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.exercise.Data;
import ra.exercise.model.entity.Question;

@Service
public class QuizService {
    @Autowired
    private Data data;

    public Question getRandomQuestion() {
        return data.getQuestion();
    }

    public boolean checkAnswer(Question question, String answer) {
        return answer.equalsIgnoreCase(question.getAnswer());
    }
}

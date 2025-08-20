package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Exam;
import ra.edu.model.entity.Question;
import ra.edu.repository.ExamRepository;
import ra.edu.repository.QuestionRepository;
import ra.edu.service.QuestionService;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> findByExamId(Long examId) {
        return questionRepository.findByExamId(examId);
    }

    @Override
    public Question findByExamIdAndQuestionId(Long examId, Long questionId) {
        return questionRepository.findByExamIdAndQuestionId(examId, questionId);
    }

    @Override
    public Question findByExamIdAndId(Long examId, Long id) {
        return questionRepository.findByExamIdAndId(examId, id);
    }

    @Override
    public Question save(Question question) {
        Exam exam = examRepository.findById(question.getExam().getId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        question.setExam(exam);
        return questionRepository.save(question);
    }

    @Override
    public Question findById(Long id) {
        if(id == null){
            return null;
        }
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null) {
            return false;
        }
        if (questionRepository.existsById(id)) {
            questionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

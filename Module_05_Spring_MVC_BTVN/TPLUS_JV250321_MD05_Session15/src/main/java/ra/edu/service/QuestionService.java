package ra.edu.service;

import ra.edu.model.entity.History;
import ra.edu.model.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findAll();
    List<Question> findByExamId(Long examId);
    Question findByExamIdAndQuestionId(Long examId, Long questionId);
    Question findByExamIdAndId(Long examId,Long id);
    Question save(Question question);
    Question findById(Long id);
    boolean deleteById(Long id);
}

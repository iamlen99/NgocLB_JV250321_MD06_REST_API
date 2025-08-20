package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByExamId(Long examId);

    @Query("FROM Question q where q.exam.id =:examId and q.id =:questionId")
    Question findByExamIdAndQuestionId(Long examId, Long questionId);

    Question findByExamIdAndId(Long examId, Long id);
}

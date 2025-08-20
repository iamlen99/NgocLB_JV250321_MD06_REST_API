package ra.edu.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Nội dung câu hỏi không được để trống")
    private String content;
    @Column(nullable = false)
    @NotBlank(message = "Đáp án không được để trống")
    private String answerA;
    @Column(nullable = false)
    @NotBlank(message = "Đáp án không được để trống")
    private String answerB;
    @Column(nullable = false)
    @NotBlank(message = "Đáp án không được để trống")
    private String answerC;
    @Column(nullable = false)
    @NotBlank(message = "Đáp án không được để trống")
    private String answerD;
    @Column(nullable = false)
    @NotBlank(message = "Đáp án không được để trống")
    private String answerTrue;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
}

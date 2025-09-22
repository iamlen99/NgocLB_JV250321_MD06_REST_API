package ra.edu.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "assessment_results",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"assignment_id", "round_id", "criteria_id"})
        }
)
public class AssessmentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private InternshipAssignment assignment;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private AssessmentRound round;

    @ManyToOne
    @JoinColumn(name = "criteria_id")
    private EvaluationCriteria criteria;

    @Column(nullable = false, columnDefinition = "DOUBLE CHECK (score > 0)")
    private Double score;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Users users;

    private LocalDate evaluationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}

package ra.edu.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "round_criterion",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"round_id", "criteria_id"})
        }
)
public class RoundCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roundCriteriaId;

    @Column(nullable = false, columnDefinition = "DOUBLE CHECK (weight > 0)")
    private Double weight;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private AssessmentRound assessmentRound;

    @ManyToOne
    @JoinColumn(name = "criteria_id")
    private EvaluationCriteria evaluationCriteria;
}

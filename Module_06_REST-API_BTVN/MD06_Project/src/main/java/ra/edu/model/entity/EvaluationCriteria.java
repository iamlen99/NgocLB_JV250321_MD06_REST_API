package ra.edu.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluation_criterion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 200)
    private String criterionName;

    private String description;

    @Column(nullable = false, columnDefinition = "DOUBLE CHECK (max_score > 0)")
    private Double maxScore;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

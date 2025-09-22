package ra.edu.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assessment_rounds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentRound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roundId;

    @Column(unique = true, nullable = false, length = 200)
    private String criterionName;

    private String description;

    @Column(nullable = false, columnDefinition = "DOUBLE CHECK (max_score > 0)")
    private Double maxScore;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}

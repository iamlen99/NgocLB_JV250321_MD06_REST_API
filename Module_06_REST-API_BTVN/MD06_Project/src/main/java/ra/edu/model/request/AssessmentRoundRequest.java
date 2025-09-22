package ra.edu.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ra.edu.model.entity.InternshipPhase;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentRoundRequest {
    private Long phaseId;

    @NotBlank(message = "Tên đợt đánh giá không được để trống")
    private String roundName;

    @NotNull(message = "Ngày bắt đầu đợt đánh giá không được để trống")
    private LocalDate startDate;

    @NotNull(message = "Ngày kết thúc đợt đánh giá không được để trống")
    private LocalDate endDate;

    private String description;
}

package ra.edu.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipPhaseRequest {
    @NotBlank(message = "Tên giai đoạn thực tập không được để trống")
    private String phaseName;

    @NotNull(message = "Ngày bắt đầu thực tập không được để trống")
    private LocalDate startDate;

    @NotNull(message = "Ngày kết thúc thực tập không được để trống")
    private LocalDate endDate;

    private String description;
}

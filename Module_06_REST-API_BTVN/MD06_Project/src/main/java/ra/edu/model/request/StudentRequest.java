package ra.edu.model.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {
    private Long studentId;

    @NotBlank(message = "Mã sinh viên không được để trống")
    private String studentCode;
    private String major;
    private String studentClass;
    private LocalDate dateOfBirth;
    private String address;
}

package ra.edu.model.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private Long studentId;

    private String fullName;

    private String studentCode;

    private String major;

    private String studentClass;

    private LocalDate dateOfBirth;

    private String address;
}

package ra.edu.model.response;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorResponse {
    private Long mentorId;

    private String fullName;

    private String department;

    private String academicRank;
}

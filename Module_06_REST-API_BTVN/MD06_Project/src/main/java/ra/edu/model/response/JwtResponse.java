package ra.edu.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private String username;

    private String fullName;

    private String email;

    private String phone;

    private Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    private String token;

    private String typeAuthentication;

    private Set<String> roles;
}

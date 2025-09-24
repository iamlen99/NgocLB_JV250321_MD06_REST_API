package ra.edu.model.response;

import lombok.*;
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

    private String token;

    private String typeAuthentication;

    private Set<String> roles;
}

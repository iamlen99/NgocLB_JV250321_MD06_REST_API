package ra.edu.model.response;

import lombok.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private final String type = "Bearer";
    private String username;
    private String fullName;
    private String email;
    private String address;
    private Boolean status;
    private String phone;
    private Set<String> roles;
    private String accessToken;
    private String refreshToken;
}

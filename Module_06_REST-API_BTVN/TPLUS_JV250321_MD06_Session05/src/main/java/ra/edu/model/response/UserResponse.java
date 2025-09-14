package ra.edu.model.response;

import lombok.*;
import ra.edu.model.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private User.Role role;
}

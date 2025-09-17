package ra.edu.model.request;


import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRegister {
    private String username;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private String password;
    private Set<String> roles;
}

package ra.edu.model.request;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLogin {
    private String username;
    private String password;
}

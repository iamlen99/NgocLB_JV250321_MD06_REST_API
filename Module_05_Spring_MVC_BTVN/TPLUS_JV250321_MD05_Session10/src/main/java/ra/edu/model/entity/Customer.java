package ra.edu.model.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer id;
    @NotEmpty(message = "Full name is empty!")
    private String fullName;
    @NotEmpty(message = "Phone number is empty!")
    private String phoneNumber;
    @NotEmpty(message = "Email is empty!")
    private String email;
    @NotEmpty(message = "Password is empty!")
    private String password;
    @NotEmpty(message = "Address is empty!")
    private String address;
    @NotNull(message = "Role is empty!")
    private Role role;
}

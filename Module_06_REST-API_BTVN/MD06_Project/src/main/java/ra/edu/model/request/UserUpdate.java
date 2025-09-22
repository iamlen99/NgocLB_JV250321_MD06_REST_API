package ra.edu.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdate {
    @NotBlank(message = "Họ tên người dùng không được để trống")
    private String fullName;

    @NotBlank(message = "Email người dùng không được để trống")
    private String email;

    @NotBlank(message = "Số điện thoại người dùng không được để trống")
    private String phone;
}

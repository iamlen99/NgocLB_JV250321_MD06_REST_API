package ra.edu.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "Email không được để trống!")
    private String email;
    @NotBlank(message = "Mật khẩu không được để trống!")
    private String password;
    @NotBlank(message = "Họ tên không được để trống!")
    private String fullName;
    private MultipartFile imageFile;
}

package ra.edu.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ra.edu.validation.NotEmptyFile;
import ra.edu.validation.NotExistEmail;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "Email không được để trống!")
    @NotExistEmail
    private String email;
    @NotBlank(message = "Mật khẩu không được để trống!")
    private String password;
    @NotBlank(message = "Họ tên không được để trống!")
    private String fullName;
    @NotEmptyFile(message = "Avatar không được để trống!")
    private MultipartFile imageFile;
}

package ra.edu.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.format.annotation.DateTimeFormat;
import ra.edu.model.entity.User;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {
    @NotBlank(message = "Mật khẩu hiện tại không được để trống!")
    private String currentPassword;

    @NotBlank(message = "Mật khẩu mới không được để trống!")
    private String newPassword;

    @NotBlank(message = "Mật khẩu xác nhận không được để trống!")
    private String confirmNewPassword;
}

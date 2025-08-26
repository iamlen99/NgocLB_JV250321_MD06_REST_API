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
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegister {

    @NotBlank(message = "Họ tên không được để trống!")
    private String name;

    @NotNull(message = "Ngày sinh không được để trống!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;

    @NotBlank(message = "Email không được để trống!")
    private String email;

    @NotNull(message = "Giới tính không được để trống!")
    private Boolean sex;

    @NotBlank(message = "Số điện thoại không được để trống!")
    private String phone;

    @NotBlank(message = "Mật khẩu không được để trống!")
    private String password;

    @NotBlank(message = "Mật khẩu xác nhận không được để trống!")
    private String confirmPassword;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .dob(this.dob)
                .email(this.email)
                .sex(this.sex)
                .phone(this.phone)
                .password(BCrypt.hashpw(this.password, BCrypt.gensalt(12)))
                .build();
    }
}

package ra.edu.model.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;
import ra.edu.model.entity.Role;
import ra.edu.validation.NotExistEmail;
import ra.edu.validation.NotExistPhone;
import ra.edu.validation.NotExistUsername;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    @NotBlank(message = "Tên người dùng không được để trống")
    @NotExistUsername
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    @NotBlank(message = "Email không được để trống")
    @NotExistEmail
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @NotExistPhone
    private String phone;

    private Role role;

}

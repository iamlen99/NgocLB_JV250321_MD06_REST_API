package ra.edu.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserNameDT0 {
    private Long id;
    @NotBlank(message = "Tên người dùng không được để trống")
    private String userName;
}

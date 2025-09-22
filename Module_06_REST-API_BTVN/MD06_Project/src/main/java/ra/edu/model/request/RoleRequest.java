package ra.edu.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
public class RoleRequest {
    @NotEmpty(message = "Danh sách role không được để trống")
    private Set<String> roles;
}

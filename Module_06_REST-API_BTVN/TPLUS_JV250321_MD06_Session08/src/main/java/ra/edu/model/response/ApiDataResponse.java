package ra.edu.model.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiDataResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private String errors;
    private HttpStatus status;
}

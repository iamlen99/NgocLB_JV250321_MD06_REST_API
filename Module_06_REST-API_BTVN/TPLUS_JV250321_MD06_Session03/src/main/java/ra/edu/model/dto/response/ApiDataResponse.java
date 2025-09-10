package ra.edu.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiDataResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private String errors;
    private HttpStatus status;
}

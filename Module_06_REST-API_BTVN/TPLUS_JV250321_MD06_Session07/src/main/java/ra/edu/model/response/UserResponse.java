package ra.edu.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

public record UserResponse(String username, String email, Set<String> roles) {}

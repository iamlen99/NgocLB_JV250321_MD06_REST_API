package ra.edu.model.response;

import java.util.Set;

public record UserResponse(String username, String email, Set<String> roles) {}

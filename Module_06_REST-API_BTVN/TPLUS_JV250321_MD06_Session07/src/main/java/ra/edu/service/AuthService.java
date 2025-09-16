package ra.edu.service;

import ra.edu.model.request.RegisterRequest;
import ra.edu.model.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);
}

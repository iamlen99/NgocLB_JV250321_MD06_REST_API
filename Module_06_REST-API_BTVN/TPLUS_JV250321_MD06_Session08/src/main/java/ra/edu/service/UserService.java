package ra.edu.service;

import ra.edu.model.entity.Users;
import ra.edu.model.request.UserLogin;
import ra.edu.model.request.UserRegister;
import ra.edu.model.response.JwtResponse;

public interface UserService {
    Users register(UserRegister userRegister);

    JwtResponse login(UserLogin userLogin);

    JwtResponse refreshAccessToken(String refreshToken);
}

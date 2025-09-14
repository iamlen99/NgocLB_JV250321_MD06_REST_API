package ra.edu.service;

import ra.edu.model.entity.Cinema;
import ra.edu.model.entity.Movie;
import ra.edu.model.request.UserRequest;
import ra.edu.model.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse register(UserRequest userRequest);
    List<UserResponse> getAllUsers(int page, int size);
}

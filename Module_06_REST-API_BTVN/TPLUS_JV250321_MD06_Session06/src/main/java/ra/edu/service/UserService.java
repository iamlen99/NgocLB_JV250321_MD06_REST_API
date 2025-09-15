package ra.edu.service;

import ra.edu.model.dto.request.UserRequest;
import ra.edu.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(UserRequest userRequest);
    User findUserById(Long id);
}

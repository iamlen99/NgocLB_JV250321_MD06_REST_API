package ra.edu.service;

import ra.edu.model.entity.User;

public interface UserService {
    User login(String username, String password);
}
